package com.jxxx.gaotang.view.fragment.mall;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseFragment;
import com.jxxx.gaotang.view.adapter.CommodityAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CommodityFragment extends BaseFragment {

    @BindView(R.id.rv_mall_shop)
    RecyclerView mRvMallShop;

    private CommodityAdapter mCommodityAdapter;
    private ArrayList<String> mList = new ArrayList<>();
    private Bundle bundle;

    @Override
    protected int getContentView() {
        return R.layout.fragment_commodity;
    }

    @Override
    protected void initViews() {
        bundle = getArguments();
        initRv();
    }

    private void initRv(){
        mRvMallShop.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvMallShop.setHasFixedSize(false);
        mCommodityAdapter = new CommodityAdapter(null);
        mRvMallShop.setAdapter(mCommodityAdapter);
        for (int i = 0;i<7;i++){
            mList.add("");
        }
        mCommodityAdapter.addData(mList);
    }

    @Override
    public void initImmersionBar() {

    }

}
