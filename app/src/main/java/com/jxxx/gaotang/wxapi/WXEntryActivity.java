package com.jxxx.gaotang.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.jxxx.gaotang.R;
import com.jxxx.gaotang.conpoment.constants.ConstValues;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
	private IWXAPI iwxapi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		iwxapi = WXAPIFactory.createWXAPI(this, ConstValues.WX_APP_ID, false);
		iwxapi.handleIntent(getIntent(), this);
	}


	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		iwxapi.handleIntent(getIntent(), this);
	}


	@Override
	public void onReq(BaseReq baseReq) {
		switch (baseReq.getType()) {
			case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//                goToGetMsg();
				break;
			case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
//                goToShowMsg((ShowMessageFromWX.Req) req);
				break;


			default:
				break;
		}
		Toast.makeText(this, baseReq.getType(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onResp(BaseResp baseResp) {
		Log.e("wkm","返回值："+baseResp.errCode);
		int result;
		switch (baseResp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
					String code = ((SendAuth.Resp) baseResp).code;
					Intent intent = new Intent("loginSuccess");
					intent.putExtra("code", code);
					LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
				} else if (baseResp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
					Toast.makeText(WXEntryActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
					finish();
				}

//                result = R.string.errcode_success;


				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				result = R.string.errcode_cancel;

				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				result = R.string.errcode_deny;
				break;
			case BaseResp.ErrCode.ERR_UNSUPPORT:
				result = R.string.errcode_unsupported;
				break;
			default:
				result = R.string.errcode_unknown;
				break;
		}
		finish();
	}

	/*private void getAccessToken(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.WX_APP_ID
				+ "&secret=" + Constant.WX_APP_APPSECRET + "&code=" + code + "&grant_type=authorization_code";
		HttpManager.getInstance().accessToken(url, new XOnRequestCallback<AccessTokenBean>() {
			@Override
			public void onSuccess(AccessTokenBean value) {
				L.e("wkm", "openId：" + value.getOpenid());
				getUserInfo(value.getAccess_token(), value.getOpenid());
			}

			@Override
			public void onFailed(XRequestError error) {

			}
		});

    }

    private void getUserInfo(String access_token, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
        HttpManager.getInstance().accessToken(url, new XOnRequestCallback<AccessTokenBean>() {
            @Override
            public void onSuccess(AccessTokenBean value) {
                L.e("wkm", "用户信息：" + value.getNickname());
                startActivity(new Intent(WXEntryActivity.this, MyShopActivity.class));


            }

            @Override
            public void onFailed(XRequestError error) {

            }
        });
    }*/
}