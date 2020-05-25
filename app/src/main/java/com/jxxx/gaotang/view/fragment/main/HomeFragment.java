package com.jxxx.gaotang.view.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseFragment;
import com.jxxx.gaotang.conpoment.widget.TabWithScrollView;
import com.jxxx.gaotang.view.adapter.HomeActivityAdapter;
import com.jxxx.gaotang.view.adapter.HomeDotcorAdapter;
import com.jxxx.gaotang.view.adapter.HomeTopicAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.tabs_scrollview)
    TabWithScrollView mTabsScrollview;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.ll_gtbk)
    LinearLayout mLlGtbk;
    @BindView(R.id.ll_jkgw)
    LinearLayout mLlJkgw;
    @BindView(R.id.ll_swbd)
    LinearLayout mLlSwbd;
    @BindView(R.id.ll_jlxt)
    LinearLayout mLlJlxt;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.ll_recommend)
    LinearLayout mLlRecommend;
    @BindView(R.id.ll_groups)
    LinearLayout mLlGroups;
    @BindView(R.id.rv_doctor)
    RecyclerView mRvDoctor;
    @BindView(R.id.rv_topic)
    RecyclerView mRvTopic;
    @BindView(R.id.rv_activity)
    RecyclerView mRvActivity;

    private HomeDotcorAdapter mHomeDotcorAdapter;
    private HomeTopicAdapter mHomeTopicAdapter;
    private HomeActivityAdapter mHomeActivityAdapter;
    private ArrayList<String> list = new ArrayList<>();

    String[] tabList = {"首页", "推荐", "部落群"};
    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        initHead();
        initHomeDoctorRv();
        initHomeTopicRv();
        initHomeActivityRv();
    }

    private void initHomeDoctorRv(){
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvDoctor.setLayoutManager(ms);
        mRvDoctor.setHasFixedSize(true);
        mHomeDotcorAdapter = new HomeDotcorAdapter(null);
        mRvDoctor.setAdapter(mHomeDotcorAdapter);
        for (int i = 0;i<10;i++){
            list.add("");
        }
        mHomeDotcorAdapter.addData(list);
    }


    private void initHomeTopicRv(){
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTopic.setLayoutManager(ms);
        mRvTopic.setHasFixedSize(true);
        mHomeTopicAdapter = new HomeTopicAdapter(null);
        mRvTopic.setAdapter(mHomeTopicAdapter);
        for (int i = 0;i<10;i++){
            list.add("");
        }
        mHomeTopicAdapter.addData(list);
    }


    private void initHomeActivityRv(){
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvActivity.setLayoutManager(ms);
        mRvActivity.setHasFixedSize(true);
        mHomeActivityAdapter = new HomeActivityAdapter(null);
        mRvActivity.setAdapter(mHomeActivityAdapter);
        for (int i = 0;i<10;i++){
            list.add("");
        }
        mHomeActivityAdapter.addData(list);
    }

    /**
     * 添加视图
     */
    private void initHead() {
        for (String tab : tabList) {
            mTabs.addTab(mTabs.newTab().setText(tab));
        }
        List<View> views = new LinkedList<>();
        views.add(mLlHome);
        views.add(mLlRecommend);
        views.add(mLlGroups);
        mTabsScrollview.setAnchorList(views); // 设置视图集合
        mTabsScrollview.setupWithTabLayout(mTabs); // 设置绑定的tabLayout
    }

    @Override
    public void initImmersionBar() {

    }
}
