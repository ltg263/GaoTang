package com.jxxx.gaotang.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import com.alibaba.security.rp.RPSDK;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.jxxx.gaotang.R;
import com.jxxx.gaotang.conpoment.constants.ConstValues;
import com.jxxx.gaotang.conpoment.utils.ActivityManager;
import com.jxxx.gaotang.conpoment.utils.RegionSelectorSourceUtil;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/8/28.
 */

public class MainApplication extends Application {

    private static LocalBroadcastManager localBroadcastManager;
    private static MainApplication INSTANCE;

    double lat = 29.8877248806424;
    double lon = 121.523712565104;
    private String districtId;
    private String localCity;
    public static String registrationId;

    @Override
    public void onCreate() {
        super.onCreate();
        if (INSTANCE == null)
            INSTANCE = this;
        ActivityManager.init(this);
        //CrashCat.getInstance(getApplicationContext(),null,null).start();
        initCommonUtil();
        initHttpUtil();
        initRegionSource();
        //initTCAgent();
        new DataCollect().collect();
        initSmartRefresh();
        initBugly();
        RPSDK.initialize(this);
        initJPush();

        registrationId = JPushInterface.getRegistrationID(this);
        SPUtils.getInstance().put(ConstValues.REGISTRATION_ID,registrationId);
        initUMShare();
    }

    private void initUMShare() {
        UMShareAPI.get(this);
    }
    {
        PlatformConfig.setWeixin("wxb3158423bca42969","2c81322d6c27733fc182fef61ca96be8");
    }


    private void initJPush(){
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    private void initBugly(){
        CrashReport.initCrashReport(getApplicationContext(), ConstValues.BUGLY_KEY, false);
    }

//    private void initTCAgent(){
//        TCAgent.LOG_ON=true;
//        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
//        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
//        TCAgent.init(this);
//        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
//        TCAgent.setReportUncaughtExceptions(true);
//    }

    private void initRegionSource(){
        RegionSelectorSourceUtil.getInstance(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }



    private void initHttpUtil(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(new CookieJarImpl(new SPCookieStore(this)))
                .connectTimeout(10*1000, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {

                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //强行返回true 即验证成功
                        return true;
                    }
                })
                .proxy(Proxy.NO_PROXY);

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(5);
    }

    public static LocalBroadcastManager getLocalBroadcastManager(){
        if (localBroadcastManager == null){
            localBroadcastManager = LocalBroadcastManager.getInstance(Utils.getApp());
        }
        return localBroadcastManager;
    }

    private void initCommonUtil(){
        Utils.init(this);
    }

    private void initSmartRefresh(){
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_ffffff, android.R.color.black);
                return new ClassicsHeader(context).setDrawableSize(10);
            }
        });
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getLocalCity() {
        return localCity;
    }

    public void setLocalCity(String localCity) {
        this.localCity = localCity;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public static MainApplication getInstance() {
        return INSTANCE;
    }
}
