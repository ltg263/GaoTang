package com.jxxx.gaotang.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jxxx.gaotang.conpoment.constants.ConstValues;
import com.jxxx.gaotang.conpoment.constants.RequestWhatI;
import com.jxxx.gaotang.request.Api;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler, RequestWhatI {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    AlertDialog.Builder builder;

    public static String orderId;
    public static String orderType;
    public static String orderNo;
    public static String payTypes;
    public static String consumptionType;
    public static String money;
    public static String shop_status;


    private Api mApi;
    private Gson mGson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mApi = new Api(handler, this);
        mGson = new Gson();
        api = WXAPIFactory.createWXAPI(this, ConstValues.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onReq(BaseReq req) {
        Log.d(TAG, "req= " + req);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
//        }
//        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}