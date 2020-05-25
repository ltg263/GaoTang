package com.jxxx.gaotang.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 作者：wzh
 * 创建时间：2018/5/22 20:01
 * NrcCommunity
 */

public class MainReceiver extends BroadcastReceiver {

    public static final String ACTION_RELOGIN = "action_user_relogin";

    private MainReceiverCallBack callBack;

    public MainReceiver(){

    }

    public MainReceiver(MainReceiverCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        callBack.onNewReceived(intent.getAction(),intent);
    }

    public interface MainReceiverCallBack{
        void onNewReceived(String action, Intent intent);
    }

}
