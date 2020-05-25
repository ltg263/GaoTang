package com.jxxx.gaotang.view.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxxx.gaotang.R;

import java.util.List;

public class HomeActivityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeActivityAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_activity,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
