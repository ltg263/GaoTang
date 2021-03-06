package com.jxxx.gaotang.conpoment.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jxxx.gaotang.conpoment.widget.GlideTransform;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context.getApplicationContext())
                .load(path)
                .transform( new GlideTransform(context,10))
                .into(imageView);
    }


    /*@Override
    public void displayImage(Context context, Object path, View imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .transform( new GlideRoundTransform(context,20))
                .into((ImageView) imageView);
    }*/

//    @Override
//    public ImageView createImageView(Context context) {
//        return new RoundAngleImageView(context);
//    }
}
