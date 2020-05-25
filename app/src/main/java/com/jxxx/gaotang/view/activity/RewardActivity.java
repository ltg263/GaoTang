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
import com.jxxx.gaotang.conpoment.widget.PileAvertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RewardActivity extends BaseActivity {

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
    @BindView(R.id.civ_head)
    CircleImageView mCivHead;
    @BindView(R.id.tv_user_money)
    TextView mTvUserMoney;
    @BindView(R.id.ll_mine_reward)
    LinearLayout mLlMineReward;
    @BindView(R.id.pv_head)
    PileAvertView mPvHead;

    @Override
    protected int getContentView() {
        return R.layout.activity_reward;
    }

    @Override
    protected void initViews() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_common_back));
        mRlActionbar.setBackground(getResources().getDrawable(R.drawable.top_gezi_bg));
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        initPvHead();
    }

    private void initPvHead(){
        List<String> urls=new ArrayList<>();
        urls.clear();
        urls.add("http://api.zjduon.com/upload/27426A393B6C3E16894B7B658618AC36.jpg");
        urls.add("http://api.zjduon.com/upload/5924311589D66F9566C56EABB416DD0D.jpg");
        urls.add("http://api.zjduon.com/upload/277C2C04922BF79A635B603EC4433672.jpg");
        //设置数据源
        mPvHead.setAvertImages(urls,3);
    }



    @OnClick({R.id.iv_back, R.id.ll_mine_reward})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ll_mine_reward:
                break;
        }
    }
}
