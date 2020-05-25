package com.jxxx.gaotang.view.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseFragment;
import com.jxxx.gaotang.conpoment.widget.AutoHeightViewPager;
import com.jxxx.gaotang.view.adapter.MallTypeAdapter;
import com.jxxx.gaotang.view.fragment.mall.CommodityFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MallFragment extends BaseFragment {

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
    @BindView(R.id.iv_rightimg_two)
    ImageView mIvRightimgTwo;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.rv_categories)
    RecyclerView mRvCategories;
    @BindView(R.id.banner_two)
    Banner mBannerTwo;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewpager)
    AutoHeightViewPager mViewpager;

    private ArrayList<String> titles = new ArrayList<>();
    private MallTypeAdapter mMallTypeAdapter;
    private ArrayList<String> mList = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.fragment_mall;
    }

    @Override
    protected void initViews() {
        mRlActionbar.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
        mIvRightimg.setImageDrawable(getResources().getDrawable(R.drawable.icon_black_search));
        mIvRightimgTwo.setImageDrawable(getResources().getDrawable(R.drawable.icon_shopcart));
        mTvTitle.setText("商城");
        mTvTitle.setTextColor(getResources().getColor(R.color.color_000000));
        initMallTypeRv();
        titles.add("推荐");
        titles.add("组合功能套餐");
        titles.add("组合");
        titles.add("组合功");
        titles.add("组合功");
        initTabs();

    }


    private void initMallTypeRv(){
        mRvCategories.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRvCategories.setHasFixedSize(true);
        mMallTypeAdapter = new MallTypeAdapter(null);
        mRvCategories.setAdapter(mMallTypeAdapter);
        for (int i = 0;i<8;i++){
            mList.add("");
        }
        mMallTypeAdapter.addData(mList);
    }


    private void initTabs() {
        final List<Fragment> mFragments = getFragments();
        mViewpager.setOffscreenPageLimit(titles.size());
        mViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                // 切换到当前页面，重置高度
                mViewpager.requestLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabs.setupWithViewPager(mViewpager);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            CommodityFragment fragment = new CommodityFragment();
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    @Override
    public void initImmersionBar() {

    }
}
