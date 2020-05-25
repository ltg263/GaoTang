package com.jxxx.gaotang.conpoment.utils;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

/**
 * Created by Administrator on 2017/9/1.
 */

public class TakePhotoUtil {

    public CropOptions getCropOptions(){
        CropOptions.Builder builder=new CropOptions.Builder();
        builder.setOutputX(800).setOutputY(800);
        builder.setWithOwnCrop(false);
        return builder.create();
    }

    public TakePhoto configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(false);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());
        return takePhoto;
    }

    public TakePhoto configCompress(TakePhoto takePhoto){
        CompressConfig config;
        config=new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(800)
                .enableReserveRaw(false)
                .create();
        takePhoto.onEnableCompress(config,true);
        return takePhoto;
    }
}
