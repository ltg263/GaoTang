package com.jxxx.gaotang.conpoment.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.jph.takephoto.model.TResult;
import com.jxxx.gaotang.conpoment.constants.ConstValues;

import java.io.File;


/**
 * 作者：wzh
 * 创建时间：2018/3/7 13:31
 * ShiDaiQianBao
 */

public class TakePhotoSuccessUtil {

    public void parsePhoto(final Context context, TResult result, final onParsePhotoFinishListener listener) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + ConstValues.FILE_DIRECTORY_IMG);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!ObjectUtils.isEmpty(result.getImage().getCompressPath())) {
            File sourceFile = new File(file.getPath() + new File(result.getImage().getCompressPath()).getName());
            FileUtils.copyFile(new File(result.getImage().getCompressPath()), sourceFile);
            new CompressUtil().compressImgWithFile(context, sourceFile, 150, file.getPath(), new CompressUtil.compressListener() {
                @Override
                public void onSuccess(File file) {
                    listener.onParsePhotoSuccess(file);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("","");
                    Toast.makeText(context, "拍摄失败,请重新拍摄!", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (!ObjectUtils.isEmpty(result.getImage().getOriginalPath())) {
            new CompressUtil().compressImgWithFile(context, new File(result.getImage().getOriginalPath()), 150, file.getPath(), new CompressUtil.compressListener() {
                @Override
                public void onSuccess(File file) {
                    listener.onParsePhotoSuccess(file);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("","");
                    Toast.makeText(context, "拍摄失败,请重新拍摄!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "拍摄失败,请重新拍摄!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface onParsePhotoFinishListener{
        void onParsePhotoSuccess(File img);
    }
}
