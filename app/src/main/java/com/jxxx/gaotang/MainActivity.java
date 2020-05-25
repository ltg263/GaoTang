package com.jxxx.gaotang;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.jxxx.gaotang.base.BaseActivity;
import com.jxxx.gaotang.view.fragment.main.ConsultantFragment;
import com.jxxx.gaotang.view.fragment.main.HomeFragment;
import com.jxxx.gaotang.view.fragment.main.HomeRecordFragment;
import com.jxxx.gaotang.view.fragment.main.MallFragment;
import com.jxxx.gaotang.view.fragment.main.MineFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    EasyNavigationBar mNavigationBar;

    private String[] tabText = {"首页", "商城", "", "顾问", "个人"};

    private HomeFragment mHomeFragment;
    private MallFragment mMallFragment;
    private HomeRecordFragment mHomeRecordFragment;
    private ConsultantFragment mConsultantFragment;
    private MineFragment mMineFragment;

    //未选中icon
    private int[] normalIcon = {R.mipmap.icon_home_unselect,R.mipmap.icon_mall_unselect, R.mipmap.icon_home_add, R.mipmap.icon_consultant_unselect, R.mipmap.icon_mine_unselect};
    //选中时icon
    private int[] selectIcon = {R.mipmap.icon_home_select,R.mipmap.icon_mall_select, R.mipmap.icon_home_add, R.mipmap.icon_consultant_select, R.mipmap.icon_mine_select};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true) .init();
        mHomeFragment = new HomeFragment();
        mMallFragment = new MallFragment();
        mHomeRecordFragment = new HomeRecordFragment();
        mConsultantFragment = new ConsultantFragment();
        mMineFragment = new MineFragment();

        fragments.add(mHomeFragment);
        fragments.add(mMallFragment);
        fragments.add(mHomeRecordFragment);
        fragments.add(mConsultantFragment);
        fragments.add(mMineFragment);

        mainNavigation();
    }

    public void mainNavigation() {
        mNavigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .tabTextSize(14)
                .anim(null)
                .normalTextColor(Color.parseColor("#666666"))
                .selectTextColor(Color.parseColor("#FF7F00"))
                .addLayoutRule(EasyNavigationBar.RULE_CENTER)
                .addLayoutBottom(0)
                .addAlignBottom(true)
                .addAsFragment(true)
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        switch (position){
                            case 0:
                                ImmersionBar.with(MainActivity.this).statusBarDarkFont(true).fitsSystemWindows(true) .init();
                                break;
                            case 1:
                                ImmersionBar.with(MainActivity.this).statusBarDarkFont(true).statusBarColor(R.color.color_ffffff).fitsSystemWindows(true) .init();
                                break;
                            case 2:
                                ImmersionBar.with(MainActivity.this).statusBarDarkFont(true).statusBarColor(R.color.color_17AA82).fitsSystemWindows(true) .init();
                                break;
                            case 3:
                                ImmersionBar.with(MainActivity.this).statusBarDarkFont(true).statusBarColor(R.color.color_00A2FF).fitsSystemWindows(true) .init();
                                break;
                            case 4:
                                ImmersionBar.with(MainActivity.this).statusBarDarkFont(true).statusBarColor(R.color.color_FF7F00).fitsSystemWindows(true) .init();
                                break;
                        }
                        return false;
                    }
                })
                .canScroll(false)
                .mode(EasyNavigationBar.MODE_ADD)
                .build();
    }
}
