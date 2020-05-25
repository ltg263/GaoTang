package com.jxxx.gaotang.JPush;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.jxxx.gaotang.conpoment.constants.ConstValues;
import com.jxxx.gaotang.request.Api;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


/**
 * 自定义接收器
 *
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JIGUANG-Example";
	PushReceiverExtra receiverExtra;
	private Api mApi;

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            receiverExtra = new Gson().fromJson(extras,PushReceiverExtra.class);
			mApi = new Api(handler,context);
			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				SPUtils.getInstance().put(ConstValues.REGISTRATION_ID,regId);
				Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

//                ToastUtils.showShort(receiverExtra.toString());
			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
//				mApi.getMsgDetail(1,receiverExtra.getInformationId());
//				initMsgJump(context);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(@NonNull Message msg) {
			super.handleMessage(msg);
		}
	};


	/*private void initMsgJump(Context context){
		switch (receiverExtra.getType()) {
			case "1":
				switch (receiverExtra.getType2()){
					case "1":
						startActivity(new Intent(context, MemberLevelActivity.class));
						break;
					case "2":
					case "4":
					case "5":
						EventBus.getDefault().post(new MainEvent("2"));
						startActivity(new Intent(context, MainActivity.class));
						ActivityCollector.removeAllActivity();
						break;

				}
				break;
			case "2":
				switch (receiverExtra.getType2()){
					case "1":
						if (receiverExtra.getType3().equals("4")){
							EventBus.getDefault().post(new MainEvent("1"));
							startActivity(new Intent(context, MainActivity.class));
							ActivityCollector.removeAllActivity();
						}else{
							if (ObjectUtils.isNotEmpty(receiverExtra.getOrderId())){
								Intent intent = new Intent(context, OrderDetailActivity.class);
								intent.putExtra("orderId", receiverExtra.getOrderId());
								if (receiverExtra.getOrderType().equals("3")){
									intent.putExtra("mall_type", "1");
								}else if (receiverExtra.getOrderType().equals("4")){
									intent.putExtra("mall_type", "2");
								}else if (receiverExtra.getOrderType().equals("5")){
									intent.putExtra("mall_type", "3");
								}else if (receiverExtra.getOrderType().equals("6")){
									intent.putExtra("mall_type", "4");
								}
								startActivity(intent);
							}
						}
						break;
					case "2":
						if (receiverExtra.getType3().equals("4")){
							startActivity(new Intent(context, LeaseListActivity.class));
						}else{
							if (ObjectUtils.isNotEmpty(receiverExtra.getOrderId())){
								Intent intent = new Intent(context, MyLeaseActivity.class);
								intent.putExtra("id", receiverExtra.getOrderId());
								startActivity(intent);
							}
						}
						break;
					case "3":
						if (ObjectUtils.isNotEmpty(receiverExtra.getOrderId())){
							Intent intent = new Intent(context, ConsumerCardDetailActivity.class);
							intent.putExtra("orderId", receiverExtra.getOrderId());
							startActivity(intent);
						}
						break;
					case "4":
						if (receiverExtra.getType3().equals("1")){
							EventBus.getDefault().post(new MainEvent("1"));
							startActivity(new Intent(context, MainActivity.class));
							ActivityCollector.removeAllActivity();
						}
						break;
				}
				break;
			case "3":
				if (ObjectUtils.isNotEmpty(receiverExtra.getProductId())){
					switch (receiverExtra.getProductType()){
						case "1":
							Intent intent = new Intent(context, ShopDetailActivity.class);
							intent.putExtra("type", "4");
							intent.putExtra("id", receiverExtra.getProductId());
							intent.putExtra("mallType", "3");
							startActivity(intent);
							break;
						case "2":
							Intent intent1 = new Intent(context, ShopDetailActivity.class);
							intent1.putExtra("type", "3");
							intent1.putExtra("id", receiverExtra.getProductId());
							intent1.putExtra("mallType", "5");
							startActivity(intent1);
							break;
						case "3":
							Intent intent2 = new Intent(context, ShopDetailActivity.class);
							intent2.putExtra("type", "6");
							intent2.putExtra("id", receiverExtra.getProductId());
							intent2.putExtra("mallType", "1");
							startActivity(intent2);
							break;
						case "4":
							Intent intent3 = new Intent(context, ShopDetailActivity.class);
							intent3.putExtra("type", "5");
							intent3.putExtra("id", receiverExtra.getProductId());
							intent3.putExtra("mallType", "2");
							startActivity(intent3);
							break;
						case "5":
							Intent intent4 = new Intent(context, ShopDetailActivity.class);
							intent4.putExtra("type", "1");
							intent4.putExtra("id", receiverExtra.getProductId());
							intent4.putExtra("mallType", "6");
							startActivity(intent4);
							break;
						case "6":
							Intent intent5 = new Intent(context, ShopDetailActivity.class);
							intent5.putExtra("type", "2");
							intent5.putExtra("id", receiverExtra.getProductId());
							intent5.putExtra("mallType", "4");
							startActivity(intent5);
							break;
					}
				}
				break;
			case "4":
				if (ObjectUtils.isNotEmpty(receiverExtra.getOrderId())){
					if (receiverExtra.getType2().equals("1")){
						if (receiverExtra.getType3().equals("1")){
							Intent intent = new Intent(context, MyLeaseActivity.class);
							intent.putExtra("id", receiverExtra.getOrderId());
							startActivity(intent);
						}else{
							Intent intent = new Intent(context, EachRepayActivity.class);
							intent.putExtra("orderId", receiverExtra.getOrderId());
							startActivity(intent);
						}
					}else if (receiverExtra.getType2().equals("2")){
						Intent intent = new Intent(context, ConsumerCardDetailActivity.class);
						intent.putExtra("orderId", receiverExtra.getOrderId());
						startActivity(intent);
					}
				}
				break;
			case "5":
				EventBus.getDefault().post(new MainEvent("1"));
				startActivity(new Intent(context, MainActivity.class));
				ActivityCollector.removeAllActivity();
				break;
			case "6":
				startActivity(new Intent(context, MyIntegralActivity.class));
				break;
			default:
				break;
		}
	}*/

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		return sb.toString();
	}

	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
				} catch (JSONException e) {

				}

//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//		}
	}
}
