package com.jxxx.gaotang.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyBindActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_family_bind;
    }

    @Override
    protected void initViews() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_common_back));
        mRlActionbar.setBackgroundColor(getResources().getColor(R.color.color_FF7F00));
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
    }

}
