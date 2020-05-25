package com.jxxx.gaotang.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jxxx.gaotang.app.MainApplication;
import com.jxxx.gaotang.conpoment.constants.ConstValues;
import com.jxxx.gaotang.conpoment.utils.JsonUtil;
import com.jxxx.gaotang.receiver.MainReceiver;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/4.
 */

public class HttpRequest {

    private Handler handler;
    private ProgressDialog progressDialog;
    private Context context;
    private static final String TAG = "HttpRequest";


    public HttpRequest(Handler handler, Context context) {
        this.handler = handler;
        progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    public void postJsonToken(final String url, final int what, String tag, JSONObject object){

        OkGo.<String>post(url)
                .headers("App-Token", SPUtils.getInstance().getString(ConstValues.TOKEN))
                .upJson(object.toString())
                .tag(tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        //判断返回数据是否为json
                        if (JsonUtil.isJsonValid(response.body())){
                            msg.obj = response.body();
                            isUserNeedRelogin(response.body().toString().trim(), msg);
                        }else {
                            msg.arg1 = -1;
                        }
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    public void postFile(final String url, final int what, String tag, final Map<String, String> paramsMap, boolean isShowProgress, List<File> fileList){
        if (isShowProgress){
            progressDialog = progressDialog.show(context,"","加载中！");
        }
        OkGo.<String>post(url)
                .headers("App-Token", SPUtils.getInstance().getString(ConstValues.TOKEN))
                .params(paramsMap,true)
                .addFileParams("file1",fileList)
                .tag(tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        //判断返回数据是否为json，如果不是先把url和传参和token写入本地文件，方便调试
                        if (JsonUtil.isJsonValid(response.body().toString().trim())){
                            msg.obj = response.body().toString().trim();
                            isUserNeedRelogin(response.body().toString().trim(), msg);
                        }else {
                            msg.arg1 = -1;
                            writeErrorLog(Environment.getExternalStorageDirectory().getPath()+ConstValues.FILE_ROOT_DIRECTORY+"/log.txt"
                                    ,url,paramsMap, SPUtils.getInstance().getString(ConstValues.TOKEN),response.body());
                        }
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        writeErrorLog(Environment.getExternalStorageDirectory().getPath()+ConstValues.FILE_ROOT_DIRECTORY+"/log.txt"
                                ,url,paramsMap, SPUtils.getInstance().getString(ConstValues.TOKEN),response.body());
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    /**
     * post请求未加密
     * @param url
     * @param what
     * @param tag
     * @param paramsMap
     * @param isShowProgress
     */
    public void postUpJson(final String url, final int what, String tag, final Map<String, String> paramsMap, boolean isShowProgress) {

        if (isShowProgress) {
            progressDialog = progressDialog.show(context, "", "加载中！");
        }
        OkGo.<String>post(url)
                .params(paramsMap, true)
                .tag(tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        //判断返回数据是否为json，如果不是先把url和传参和token写入本地文件，方便调试
                        if (JsonUtil.isJsonValid(response.body().toString().trim())) {
                            msg.obj = response.body().toString().trim();
                            isUserNeedRelogin(response.body().toString().trim(), msg);
                        } else {
                            msg.arg1 = -1;
                            writeErrorLog(Environment.getExternalStorageDirectory().getPath() + ConstValues.FILE_ROOT_DIRECTORY + "/log.txt"
                                    , url, paramsMap, SPUtils.getInstance().getString(ConstValues.TOKEN), response.body());
                        }
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        writeErrorLog(Environment.getExternalStorageDirectory().getPath() + ConstValues.FILE_ROOT_DIRECTORY + "/log.txt"
                                , url, paramsMap, SPUtils.getInstance().getString(ConstValues.TOKEN), response.body());
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    /**
     * post请求加密
     * @param url
     * @param what
     * @param tag
     * @param paramsMap
     * @param isShowProgress
     */
    public void postUpJsonToken(final String url, final int what, String tag, final Map<String, String> paramsMap, boolean isShowProgress) {

        if (isShowProgress) {
            progressDialog = progressDialog.show(context, "", "加载中！");
        }
        OkGo.<String>post(url)
                .headers("App-Token", SPUtils.getInstance().getString(ConstValues.TOKEN))
                .params(paramsMap, true)
                .tag(tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        //判断返回数据是否为json，如果不是先把url和传参和token写入本地文件，方便调试
                        if (JsonUtil.isJsonValid(response.body().toString().trim())) {
                            msg.obj = response.body().toString().trim();
                            isUserNeedRelogin(response.body().toString().trim(), msg);
                        } else {
                            msg.arg1 = -1;
                            writeErrorLog(Environment.getExternalStorageDirectory().getPath() + ConstValues.FILE_ROOT_DIRECTORY + "/log.txt"
                                    , url, paramsMap, SPUtils.getInstance().getString(ConstValues.TOKEN), response.body());
                        }
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        writeErrorLog(Environment.getExternalStorageDirectory().getPath() + ConstValues.FILE_ROOT_DIRECTORY + "/log.txt"
                                , url, paramsMap, SPUtils.getInstance().getString(ConstValues.TOKEN), response.body());
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    /**
     * get请求未加密
     * @param url
     * @param what
     * @param isShowProgress
     */
    public void getData(String url, final int what, boolean isShowProgress) {
        if (isShowProgress) {
            progressDialog = progressDialog.show(context, "", "加载中！");
        }
        OkGo.<String>get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.obj = response.body();
                        isUserNeedRelogin(response.body().toString().trim(), msg);
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    /**
     * get请求加密
     * @param url
     * @param what
     * @param isShowProgress
     */
    public void getTokenData(String url, final int what, boolean isShowProgress) {
        if (isShowProgress) {
            progressDialog = progressDialog.show(context, "", "加载中！");
        }
        OkGo.<String>get(url)
                .headers("App-Token", SPUtils.getInstance().getString(ConstValues.TOKEN))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.obj = response.body();
                        isUserNeedRelogin(response.body().toString().trim(), msg);
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    public void getBitmapTokenData(String url, final int what, boolean isShowProgress) {
        if (isShowProgress) {
            progressDialog = progressDialog.show(context, "", "加载中！");
        }
        OkGo.<Bitmap>get(url)
                .headers("App-Token", SPUtils.getInstance().getString(ConstValues.TOKEN))
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.obj = response.body();
                        isUserNeedRelogin(response.body().toString().trim(), msg);
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Response<Bitmap> response) {
                        progressDialog.dismiss();
                        Message msg = handler.obtainMessage();
                        msg.what = what;
                        msg.arg1 = -1;
                        handler.sendMessage(msg);
                        super.onError(response);
                    }
                });
    }

    /**
     * token失效操作
     *
     * @param sourceData
     * @param msg
     */
    private void isUserNeedRelogin(String sourceData, Message msg) {
        try {
            JSONObject jsonObject = new JSONObject(sourceData);
            int code = jsonObject.getInt("status");
            if (code==101){
                //token 失效
                msg.arg1 = -1;
                sendReloginBroadCast();
            }

        } catch (Exception e) {
            Log.e("isUserNeedRelogin", "json resolve fail !");
        }
    }

    /**
     * token失效发送广播
     */
    private void sendReloginBroadCast() {
        Intent intent = new Intent(MainReceiver.ACTION_RELOGIN);
        MainApplication.getLocalBroadcastManager().sendBroadcast(intent);
    }

    /**
     * 日志打印
     * @param filePath
     * @param url
     * @param paramsMap
     * @param token
     * @param content
     */
    private void writeErrorLog(String filePath, String url, Map<String, String> paramsMap, String token, String content){
        if (FileUtils.createOrExistsFile(filePath)){
            File file = new File(filePath);
            FileIOUtils.writeFileFromString(file,"\r\n",true);
            FileIOUtils.writeFileFromString(file,url,true);
            FileIOUtils.writeFileFromString(file,"\r\n",true);
            if (paramsMap!=null && paramsMap.size()>0){

                StringBuffer sb = new StringBuffer();
                for (Map.Entry<String, String> entry : paramsMap.entrySet()){
                    sb.append(entry.getKey()+"="+entry.getValue()+";");
                }
                FileIOUtils.writeFileFromString(file,sb.toString(),true);
            }
            FileIOUtils.writeFileFromString(file,"\r\n"+token+"\r\n",true);
            FileIOUtils.writeFileFromString(file,content,true);
            FileIOUtils.writeFileFromString(file,".............................................................................................."
                    ,true);
        }
    }
}
