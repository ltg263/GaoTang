package com.jxxx.gaotang.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseFragment;
import com.jxxx.gaotang.conpoment.widget.MyRadioGroup;
import com.jxxx.gaotang.view.activity.RewardActivity;
import com.jxxx.gaotang.view.adapter.ConsultantDoctorAdapter;
import com.jxxx.gaotang.view.adapter.HomeDotcorAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ConsultantFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, OnRefreshListener, OnLoadMoreListener {

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
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.rv_doctor)
    RecyclerView mRvDoctor;
    @BindView(R.id.rb_nutritionist)
    RadioButton mRbNutritionist;
    @BindView(R.id.rb_specialist)
    RadioButton mRbSpecialist;
    @BindView(R.id.mgp_doctor)
    MyRadioGroup mMgpDoctor;
    @BindView(R.id.rv_all_doctor)
    RecyclerView mRvAllDoctor;


    private HomeDotcorAdapter mHomeDotcorAdapter;
    private ConsultantDoctorAdapter mConsultantDoctorAdapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.fragment_consultant;
    }

    @Override
    protected void initViews() {
        mRlActionbar.setBackgroundColor(getResources().getColor(R.color.color_00A2FF));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_search));
        mTvTitle.setText("健康顾问");
        initHomeDoctorRv();
        initConsultantDoctorRv();
    }

    private void initHomeDoctorRv() {
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvDoctor.setLayoutManager(ms);
        mRvDoctor.setHasFixedSize(true);
        mHomeDotcorAdapter = new HomeDotcorAdapter(null);
        mRvDoctor.setAdapter(mHomeDotcorAdapter);
        for (int i = 0; i < 4; i++) {
            list.add("");
        }
        mHomeDotcorAdapter.addData(list);
    }

    private void initConsultantDoctorRv() {
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.VERTICAL);
        mRvAllDoctor.setLayoutManager(ms);
        mRvAllDoctor.setHasFixedSize(true);
        mConsultantDoctorAdapter = new ConsultantDoctorAdapter(null);
        mRvAllDoctor.setAdapter(mConsultantDoctorAdapter);
        mConsultantDoctorAdapter.setOnItemChildClickListener(this);
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mConsultantDoctorAdapter.addData(list);
    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.btn_detail:
                startActivity(new Intent(getActivity(), RewardActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
