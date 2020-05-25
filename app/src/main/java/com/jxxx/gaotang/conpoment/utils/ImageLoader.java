package com.jxxx.gaotang.conpoment.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lxj.xpopup.interfaces.XPopupImageLoader;

import java.io.File;

public abstract class ImageLoader implements XPopupImageLoader {

    @Override
    public void loadImage(int position, @NonNull Object uri, @NonNull final ImageView imageView) {

        int w = 1000* ScreenUtils.getScreenWidth()/ ScreenUtils.getScreenHeight();
        Glide.with(imageView).load(uri).apply(
                new RequestOptions()
//                        .override(w,w)
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))

                .into(imageView);

//        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();
//        Glide.with(imageView.getContext()).load(uri).apply(options).into(new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                if(drawable!=null){
//                    imageView.setImageDrawable(drawable);
//                }
//            }
//        });

    }

    //必须实现这个方法，返回uri对应的缓存文件，可参照下面的实现，内部保存图片会用到。如果你不需要保存图片这个功能，可以返回null。
    @Override
    public File getImageFile(@NonNull Context context, @NonNull Object uri) {
        try {
            return Glide.with(context).downloadOnly().load(uri).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void displayImage(Context context, Object path, ImageView imageView);
}