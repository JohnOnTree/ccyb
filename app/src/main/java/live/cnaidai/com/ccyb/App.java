package live.cnaidai.com.ccyb;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.zhy.http.okhttp.cookie.PersistentCookieStore;

import cn.jpush.android.api.JPushInterface;
import live.cnaidai.com.ccyb.util.LruCacheUtil;
import live.cnaidai.com.ccyb.wxapi.entity.TencentConstants;
import live.cnaidai.com.ccyb.wxapi.entity.WxConstants;

public class App extends Application {

    private static App sApp;

    private static Context mContext;

    public static String vCode = "";

    private PersistentCookieStore mPersistentCookieStore ;

    public static IWXAPI sApi;

    public static LruCacheUtil sLruCacheUtil;

    public static Tencent sTencent ;

    public static double latitude ;
    public static double longitude ;
    public static String sCity ;

    public static String sRegistrationId = "" ;

    public static int wxPayStatus   =  0;///999代表微信支付成功

    public static int wxOauthStatus = 0 ;//100代表授权成功
    public static String wxOpenId = "" ;//openId




    public static App getInstance() {
        if (null == sApp) {
            sApp = new App();
        }
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        if(BuildConfig.LOG_DEBUG) {
            Logger.init("TAG")
                    .setMethodCount(3)
                    .hideThreadInfo().setLogLevel(LogLevel.FULL);
        }else{
            Logger.init("TAG")
                    .setMethodCount(3)
                    .hideThreadInfo().setLogLevel(LogLevel.NONE);
        }

        sLruCacheUtil = new LruCacheUtil();

        Fresco.initialize(this);

        regToWx();

        sTencent = Tencent.createInstance(TencentConstants.APP_ID, this.getApplicationContext());


        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取持久化的cookie
     * @return
     */
    public PersistentCookieStore getMyCookieStore(){
        if(mPersistentCookieStore == null){
            mPersistentCookieStore = new PersistentCookieStore(mContext);
        }
        return mPersistentCookieStore;
    }

    public void clearCookies(Context context){
        if(mPersistentCookieStore == null){
            mPersistentCookieStore = new PersistentCookieStore(context);
        }

        mPersistentCookieStore.removeAll();
    }

    private void regToWx() {
        sApi = initWeiXin(this, WxConstants.APP_ID);
    }


    /**
     * 微信组件注册初始化
     *
     * @param context
     * @param weixin_app_id
     * @return
     */
    public  IWXAPI initWeiXin(Context context, @NonNull String weixin_app_id) {
        if (TextUtils.isEmpty(weixin_app_id)) {
            Toast.makeText(context.getApplicationContext(), "app_id 不能为空", Toast.LENGTH_SHORT).show();
        }
        IWXAPI api = WXAPIFactory.createWXAPI(context, weixin_app_id, true);
        api.registerApp(weixin_app_id);
        return api;
    }






}
