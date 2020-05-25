package com.jxxx.gaotang.base;

import com.gyf.immersionbar.components.SimpleImmersionFragment;


public abstract class LazyFragment extends SimpleImmersionFragment {
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//frahment从不可见到完全可见的时候，会调用该方法
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            //isInit=false;
            onInvisible();
        }
    }

    protected abstract void lazyLoad();//懒加载的方法,在这个方法里面我们为Fragment的各个组件去添加数据
    protected abstract void nolazyLoad();//懒加载的方法,在这个方法里面我们为Fragment的各个组件去添加数据

    protected void onVisible(){
        lazyLoad();
    }

    protected void onInvisible(){
        nolazyLoad();
    }
}
