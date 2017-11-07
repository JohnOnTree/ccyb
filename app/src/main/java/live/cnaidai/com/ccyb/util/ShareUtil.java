package live.cnaidai.com.ccyb.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.wxapi.BaseUiListener;

/**
 * Created by ChristLu on 17/1/5.
 */

public class ShareUtil {

    private static BaseUiListener listener ;

    public static void share2Wx(String url,int type){
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = BuildConfig.SERVER_PATH+url ;

        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = "来从从，这里找到你的梦中情人";
        msg.description = "想约女神在电影院缠绵吗？想来一场无限激情的约会吗？我们这里什么都有！";

        Bitmap thumb = BitmapFactory.decodeResource(App.getContext().getResources(), R.mipmap.xiaologo);
        msg.thumbData = Util.bmpToByteArray(thumb,true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg ;

        if(type == 0) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }else if(type == 1){
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }
        App.sApi.sendReq(req);
    }


    private static  String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    public static  void share2QQ(Activity ctx,String url,int type,BaseUiListener listener) {
        final Bundle params = new Bundle();
         params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "想约女神在电影院缠绵吗？想来一场无限激情的约会吗？我们这里什么都有！");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "从从");
        if(type==2) {
            App.sTencent.shareToQQ(ctx, params, listener);
        }else {
            App.sTencent.shareToQzone(ctx, params, listener);
        }
    }

}
