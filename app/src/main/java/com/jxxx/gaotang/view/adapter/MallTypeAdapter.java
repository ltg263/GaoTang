package com.jxxx.gaotang.view.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.gaotang.R;


import java.util.List;

public class MallTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MallTypeAdapter(@Nullable List<String> data) {
        super(R.layout.item_mall_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}
