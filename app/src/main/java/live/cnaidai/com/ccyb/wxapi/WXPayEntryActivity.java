package live.cnaidai.com.ccyb.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.util.CCToast;
import live.cnaidai.com.ccyb.wxapi.entity.WxConstants;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, WxConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        switch (resp.errCode) {
            case 0:
                App.wxPayStatus = 999 ;

                break;
            case -1:
                CCToast.showShort(this, "支付失败");
                break;
            case -2:
                CCToast.showShort(this, "取消支付");
                break;
        }
        finish();

    }



}