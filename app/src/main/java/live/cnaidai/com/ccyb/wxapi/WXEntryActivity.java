package live.cnaidai.com.ccyb.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.api.HomeAPI;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.wxapi.entity.WxConstants;
import live.cnaidai.com.ccyb.wxapi.entity.WxOauthEntity;

/**
 * Created by ChristLu on 17/1/5.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        finish();
        App.sApi.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        App.sApi.handleIntent(getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp instanceof SendAuth.Resp) {
            SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
            //获取微信传回的code
            String code = newResp.code;
            Log.i("newRespnewResp", "   code    :" + code);


            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    Log.i("savedInstanceState", "发送成功ERR_OKERR_OK");
                    //发送成功
                    //获取用户消息
                    App.wxOauthStatus = 100;
                    getOpenID(code);
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Log.i("savedInstanceState", "发送取消ERR_USER_CANCEL");
                    //发送取消
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    Log.i("savedInstanceState", "发送取消ERR_AUTH_DENIEDERR_AUTH_DENIEDERR_AUTH_DENIED");
                    //发送被拒绝
                    break;
                default:
                    Log.i("savedInstanceState", "发送返回breakbreakbreak");
                    //发送返回
                    break;
            }
        }
        finish();
    }

    private void getOpenID(String code) {
        // APP_ID和APP_Secret在微信开发平台添加应用的时候会生成，grant_type 用默认的"authorization_code"即可.
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxConstants.APP_ID + "&secret=" + WxConstants.APP_SECRET +
                "&code=" + code + "&grant_type=authorization_code";

        HomeAPI.getInstance().getWxUserInfo(new ResultCallback<WxOauthEntity>() {
            @Override
            public void onResponse(WxOauthEntity response) {
                super.onResponse(response);
                Log.d(TAG, "onResponse: resp ===" + response);
                App.wxOpenId = response.getOpenid() ;
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }
        }, urlStr);
    }

}
