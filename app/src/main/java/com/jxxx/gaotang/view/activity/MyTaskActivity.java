package com.jxxx.gaotang.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTaskActivity extends BaseActivity {


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
    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.tv_today_integral)
    TextView mTvTodayIntegral;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.tv_all_integral)
    TextView mTvAllIntegral;
    @BindView(R.id.iv_qd)
    ImageView mIvQd;
    @BindView(R.id.tv_qd)
    TextView mTvQd;
    @BindView(R.id.iv_jkjl)
    ImageView mIvJkjl;
    @BindView(R.id.tv_jkjl)
    TextView mTvJkjl;
    @BindView(R.id.iv_blqhd)
    ImageView mIvBlqhd;
    @BindView(R.id.tv_blqhd)
    TextView mTvBlqhd;
    @BindView(R.id.iv_bind)
    ImageView mIvBind;
    @BindView(R.id.tv_bind)
    TextView mTvBind;
    @BindView(R.id.iv_tjhy)
    ImageView mIvTjhy;
    @BindView(R.id.tv_tjhy)
    TextView mTvTjhy;
    @BindView(R.id.iv_yjfk)
    ImageView mIvYjfk;
    @BindView(R.id.tv_yjfk)
    TextView mTvYjfk;
    @BindView(R.id.iv_tg)
    ImageView mIvTg;
    @BindView(R.id.tv_tg)
    TextView mTvTg;
    @BindView(R.id.iv_xxws)
    ImageView mIvXxws;
    @BindView(R.id.tv_xxws)
    TextView mTvXxws;
    @BindView(R.id.iv_hd)
    ImageView mIvHd;
    @BindView(R.id.tv_hd)
    TextView mTvHd;

    @Override
    protected int getContentView() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void initViews() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_common_back));
        mRlActionbar.setBackground(getResources().getDrawable(R.drawable.top_gezi_bg));
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
    }


    @OnClick({R.id.iv_back, R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ll_back:
                break;
        }
    }
}
