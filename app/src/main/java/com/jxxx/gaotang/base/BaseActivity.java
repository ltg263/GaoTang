package com.jxxx.gaotang.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.jxxx.gaotang.conpoment.constants.ConstValues;
import com.jxxx.gaotang.conpoment.constants.RequestWhatI;
import com.jxxx.gaotang.conpoment.utils.ActivityCollector;
import com.jxxx.gaotang.request.Api;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.util.Stack;

import butterknife.ButterKnife;


/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */
public abstract class BaseActivity extends AppCompatActivity implements RequestWhatI {

    protected RxPermissions mRxPermissions;
    protected Gson mGson;
    protected Stack<Fragment> mFragStack;
    protected int statusBarHeight;
    protected Api mApi;
    //protected SwipeBackLayout mSwipeBackLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.base_blue_style);//修改主题
        setContentView(getContentView());
        ButterKnife.bind(this);
        statusBarHeight = BarUtils.getStatusBarHeight();
        mRxPermissions = new RxPermissions(this);
        requestPermissions();
        mGson = new Gson();
//        setSwipeBackEnable(true);
//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//        mSwipeBackLayout.setEdgeSize(400);
        initViews();
        ActivityCollector.addActivity(this, getClass());
    }

    protected abstract int getContentView();
    protected abstract void initViews();

    private void requestPermissions(){
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE)
                .subscribe(new io.reactivex.functions.Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean){
                            Toast.makeText(BaseActivity.this,"为保证应用正常运行,请在权限管理开启必要权限!", Toast.LENGTH_SHORT).show();
                        }else {
                            File file = new File(Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY);
                            if (!file.exists()){
                                file.mkdirs();
                            }
                        }
                    }
                });
    }

    public boolean ifNotLoginTurnToLogin(){
        /*if (!SPUtils.getInstance().getBoolean(ConstValues.ISLOGIN)){
            startAcvityWithNoData(this, LoginActivity.class);
            return false;
        }*/
        return true;
    }

    public void startAcvityWithNoData(Context context, Class<?> cls){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
    }

    public void hideFragment(Fragment fragment){
        if (fragment!=null & mFragStack !=null){
            getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void showFragment(Fragment fragment, boolean hidePrevius){
        if (fragment!=null & mFragStack !=null){
            for (Fragment fragment1:mFragStack){
                if (fragment1 == fragment){
                    getSupportFragmentManager().beginTransaction().show(fragment1).commitAllowingStateLoss();
                }else {
                    if (hidePrevius){
                        getSupportFragmentManager().beginTransaction().hide(fragment1).commitAllowingStateLoss();
                    }
                }
            }
        }
    }

    public int getStatusBarHeight(){
        return statusBarHeight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

}
