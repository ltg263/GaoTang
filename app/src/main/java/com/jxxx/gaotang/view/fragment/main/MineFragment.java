package com.jxxx.gaotang.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseFragment;
import com.jxxx.gaotang.view.activity.FamilyBindActivity;
import com.jxxx.gaotang.view.activity.MyTaskActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineFragment extends BaseFragment {

    @BindView(R.id.civ_head)
    CircleImageView mCivHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.ll_consultant)
    LinearLayout mLlConsultant;
    @BindView(R.id.ll_advisory)
    LinearLayout mLlAdvisory;
    @BindView(R.id.ll_family_bind)
    LinearLayout mLlFamilyBind;
    @BindView(R.id.ll_profile)
    LinearLayout mLlProfile;
    @BindView(R.id.ll_my_client)
    LinearLayout mLlMyClient;
    @BindView(R.id.ll_my_bill)
    LinearLayout mLlMyBill;
    @BindView(R.id.ll_my_task)
    LinearLayout mLlMyTask;
    @BindView(R.id.ll_my_commission)
    LinearLayout mLlMyCommission;
    @BindView(R.id.ll_invite)
    LinearLayout mLlInvite;
    @BindView(R.id.ll_complaint)
    LinearLayout mLlComplaint;
    @BindView(R.id.ll_newbie_tutorial)
    LinearLayout mLlNewbieTutorial;
    @BindView(R.id.ll_activity_record)
    LinearLayout mLlActivityRecord;
    @BindView(R.id.banner_two)
    Banner mBannerTwo;
    Unbinder unbinder;

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void initImmersionBar() {

    }


    @OnClick({R.id.civ_head, R.id.ll_consultant, R.id.ll_advisory, R.id.ll_family_bind, R.id.ll_profile, R.id.ll_my_client, R.id.ll_my_bill, R.id.ll_my_task, R.id.ll_my_commission, R.id.ll_invite, R.id.ll_complaint, R.id.ll_newbie_tutorial, R.id.ll_activity_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_head:
                break;
            case R.id.ll_consultant:
                break;
            case R.id.ll_advisory:
                break;
            case R.id.ll_family_bind:
                startActivity(new Intent(getActivity(), FamilyBindActivity.class));
                break;
            case R.id.ll_profile:
                break;
            case R.id.ll_my_client:
                break;
            case R.id.ll_my_bill:
                break;
            case R.id.ll_my_task:
                startActivity(new Intent(getActivity(), MyTaskActivity.class));
                break;
            case R.id.ll_my_commission:
                break;
            case R.id.ll_invite:
                break;
            case R.id.ll_complaint:
                break;
            case R.id.ll_newbie_tutorial:
                break;
            case R.id.ll_activity_record:
                break;
        }
    }
}
