package com.jxxx.gaotang.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseFragment;
import com.jxxx.gaotang.conpoment.widget.RoundProgressBar;
import com.jxxx.gaotang.view.activity.ClockActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeRecordFragment extends BaseFragment {

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
    @BindView(R.id.rl_smart_record)
    RelativeLayout mRlSmartRecord;
    @BindView(R.id.rpb)
    RoundProgressBar mRpb;
    @BindView(R.id.tv_day)
    TextView mTvDay;
    @BindView(R.id.tv_value)
    TextView mTvValue;
    @BindView(R.id.tv_unit)
    TextView mTvUnit;
    @BindView(R.id.rl_history_record)
    RelativeLayout mRlHistoryRecord;
    @BindView(R.id.iv_ysjl)
    ImageView mIvYsjl;
    @BindView(R.id.tv_ysjl)
    TextView mTvYsjl;
    @BindView(R.id.iv_ydjl)
    ImageView mIvYdjl;
    @BindView(R.id.tv_ydjl)
    TextView mTvYdjl;
    @BindView(R.id.iv_xyjl)
    ImageView mIvXyjl;
    @BindView(R.id.tv_xyjl)
    TextView mTvXyjl;
    @BindView(R.id.iv_yyjl)
    ImageView mIvYyjl;
    @BindView(R.id.tv_yyjl)
    TextView mTvYyjl;
    @BindView(R.id.iv_smjl)
    ImageView mIvSmjl;
    @BindView(R.id.tv_smjl)
    TextView mTvSmjl;
    @BindView(R.id.iv_thxhdb)
    ImageView mIvThxhdb;
    @BindView(R.id.tv_thxhdb)
    TextView mTvThxhdb;
    Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_record;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("健康记录");
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_clock));
        mRlActionbar.setBackgroundColor(getResources().getColor(R.color.color_17AA82));
    }

    @Override
    public void initImmersionBar() {

    }

    @OnClick({R.id.iv_back, R.id.ll_back, R.id.rl_smart_record, R.id.rl_history_record,R.id.iv_rightimg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ll_back:
                break;
            case R.id.iv_rightimg:
                startActivity(new Intent(getActivity(), ClockActivity.class));
                break;
            case R.id.rl_smart_record:
                break;
            case R.id.rl_history_record:
                break;
        }
    }
}
