package live.cnaidai.com.ccyb.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.tauth.Tencent;

import java.net.HttpCookie;
import java.util.List;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.MainV2Activity;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.alipay.AliPayUtil;
import live.cnaidai.com.ccyb.alipay.AlipayInfo;
import live.cnaidai.com.ccyb.alipay.PayResult;
import live.cnaidai.com.ccyb.api.GiftsAPI;
import live.cnaidai.com.ccyb.api.HomeAPI;
import live.cnaidai.com.ccyb.api.UserAPI;
import live.cnaidai.com.ccyb.home.pojo.CanInvitePOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.util.ACache;
import live.cnaidai.com.ccyb.util.CCToast;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ShareUtil;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.wxapi.BaseUiListener;
import live.cnaidai.com.ccyb.wxapi.entity.WxPayInfo;


/**
 * @author lujs
 * @version V1.0
 * @Package com.aidai.activity
 * @Description: 用于加载所有网页显示的界面
 * @date 2015/8/30 14:52
 */
public class CommonWebActivity extends BaseActivity {

    private LinearLayout mRoot;
    private WebView webView;
    private String url;
    private String title;

    private ProgressBar mProgressBar;

    private App mApp;

    private BaseUiListener mBaseUiListener;

    private boolean isSetting;
    /**
     * 首页   约票和被约进入
     */
    private boolean isHomePage;

    private boolean isYue;

    private boolean jiuYue;

    private String type;


    private boolean isClose;

    private boolean isBackToList;

    private boolean isJiuBackToList;


    private String currentTitle;

    private String currentUrl = "";

    private int objId;


    private Bitmap mBitmap;


    private ValueCallback<Uri> mUploadMessage;

    private static final int FILE_SELECT_CODE = 0;

    public ValueCallback<Uri[]> mUploadMessageForAndroid5;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 10000;


    private String wxIntentUrl = "";//wx提现跳转的url

    //背景色
    private int titleColor = Color.WHITE;

    private boolean closeWeb ;//是否关闭webview


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_base_webview);

        webView = (WebView) findViewById(R.id.webView);
        mRoot = (LinearLayout) findViewById(R.id.root);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        //获得标题和url
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString("url");
            title = bundle.getString("title");
            isSetting = bundle.getBoolean("setting");
            isHomePage = bundle.getBoolean("isHomePage");
            type = bundle.getString("type");
            isYue = bundle.getBoolean("isYue");
            jiuYue = bundle.getBoolean("JiuYue");
            objId = bundle.getInt("objId");
            closeWeb = bundle.getBoolean("closeWeb");
            if (bundle.containsKey("titleColor")) {
                titleColor = bundle.getInt("titleColor");
            }
        }

        if (titleColor == Color.WHITE) {
            mTvTitle.setTextColor(Color.BLACK);
            mIvLeft.setImageResource(R.mipmap.icon_back);
        } else {
            mTvTitle.setTextColor(Color.WHITE);
            mIvLeft.setImageResource(R.mipmap.icon_back_white);
        }

        mRlTitleLayout.setBackgroundColor(titleColor);
        viewDivider.setBackgroundColor(titleColor);


        setMainTitle(title);
        if (!url.contains("appType")) {
            if (url.contains("?")) {
                url = url + "&appType=3";
            } else {
                url = url + "?appType=3";
            }
        }


        synCookies(mContext, url);


        if (isSetting) {
            //显示注销按钮
            setRightTv(View.VISIBLE, "注销", onExitClickListener);
        }

        if (isYue) {
            setRightTv(View.VISIBLE, "被约记录", onRecordClickListener);
        }

        if (jiuYue) {
            setRightTv(View.VISIBLE, "被约记录", onJiuRecordClickListener);
        }


        if (objId != 0) {
            //显示右上角约会按钮
            setRightIv(R.mipmap.yueren, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳到约会页面
                    //判断是否可约票
                    HomeAPI.getInstance().canInvite(new ResultCallback<CanInvitePOJO>() {
                        @Override
                        public void onResponse(CanInvitePOJO response) {
                            super.onResponse(response);
                            if (response.isResultSuccess()) {
                                if (response.canInvite()) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", BuildConfig.SERVER_PATH + "invitation_invitationView.do?appType=3&choiceMemberId=" + objId + "&memberId=" + UserInfoUtils.getMemberId());
                                    bundle.putString("title", "约票");
                                    CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
                                } else {
                                    //弹出对话框
                                    showInviteResultDialog(response.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onError(Request request, Exception e) {
                            super.onError(request, e);
                        }
                    }, UserInfoUtils.getMemberId(), objId + "");
                }

            });

        }


//		showLoadDialog();
        //显示网页
        webView.loadUrl(url);

        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setDisplayZoomControls(false);

        webView.getSettings().setLoadWithOverviewMode(true);
        // 设置js可用
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new JavaScriptInterface(), "AppFunction");
        //添加客户端支持
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("xxx提示").setMessage(message).setPositiveButton("确定", null);
                builder.setCancelable(false);
                builder.setIcon(R.mipmap.ic_launcher);
                AlertDialog dialog = builder.create();
                dialog.show();
                result.confirm();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //进度显示
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);

            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!title.contains("appType=3")) {
                    setMainTitle(title);
                }
                currentTitle = title;
                if ("我的约票".equals(currentTitle) || "被约记录".equals(currentTitle) || "酒吧约会".equals(currentTitle)) {
                    isClose = true;
                    currentUrl = view.getUrl();
                } else {
                    isClose = false;
                }
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]>
                    filePathCallback, FileChooserParams fileChooserParams) {
                mUploadMessageForAndroid5 = filePathCallback;
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("image/*");

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

                startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);

                return true;
            }


            // For Android 4.1

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_SELECT_CODE);

            }
        });
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("undefined")) {
                    url = url.replace("undefined", "3");
                }

                if (!url.contains("appType")) {
                    if (url.contains("?")) {
                        url = url + "&appType=3";
                    } else {
                        url = url + "?appType=3";
                    }
                }


                Logger.d(currentUrl + "----" + currentTitle);

//                if("我的约票".equals(currentTitle)|| "被约记录".equals(currentTitle)){
//                    currentUrl = url;
//                }

                if ("约票完成".equals(currentTitle) || "约票评价".equals(currentTitle) || "配对成功".equals(currentTitle)
                        || "配对中".equals(currentTitle) || "待答复".equals(currentTitle)
                        || "酒吧约会详情".equals(currentTitle)) {
                    isBackToList = true;
                } else {
                    isBackToList = false;
                }

//                if("酒吧约会详情".equals(currentTitle)){
//
//                }

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                Logger.d(url+"----"+currentTitle);
//                if("我的约票".equals(currentTitle) || "被约记录".equals(currentTitle)){
//                    currentUrl = url;
//                }

            }
        });


        setLeftButtonVisible(View.VISIBLE, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack() && !(isSetting || isHomePage) && !"3".equals(type) && !isClose && !closeWeb) {
            if (isBackToList) {
                Bundle bundle = new Bundle();
                bundle.putString("url", currentUrl);
                bundle.putString("title", "我的约票");
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
                finish();
            } else {
                webView.goBack();
            }
        } else {
            finish();
        }
    }

    private void showInviteResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setMessage(message);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = new Bundle();
                bundle.putString("url", BuildConfig.SERVER_PATH + "mobileMember_toShowVIPService.do?appType=3&memberId=" + UserInfoUtils.getMemberId());
                bundle.putString("title", "VIP");
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }


    final class JavaScriptInterface {


        /**
         * 调用登录
         */
        @JavascriptInterface
        public void appLogin() {
            CommonUtils.launchActivity(CommonWebActivity.this, LoginV2Activity.class);
            finish();
        }


        /**
         * 调用登录
         */
        @JavascriptInterface
        public void appLogin(String appUrl) {
            Logger.d(url);
            if (UserInfoUtils.isLogin()) {
                url = BuildConfig.SERVER_PATH + appUrl + "&memberId=" + UserInfoUtils.getMemberId();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                bundle.putString("title", "付款确认");
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("url", BuildConfig.SERVER_PATH + appUrl);
                bundle.putBoolean("isPay", true);
                CommonUtils.launchActivity(CommonWebActivity.this, LoginV2Activity.class, bundle);
            }
        }


        @JavascriptInterface
        public void appShare(String shareParams) {

            mBaseUiListener = new BaseUiListener();

            if (shareParams.contains("type=0")) {
                //微信好友
                ShareUtil.share2Wx(shareParams, 0);
            } else if (shareParams.contains("type=1")) {
                //朋友圈
                ShareUtil.share2Wx(shareParams, 1);
            } else if (shareParams.contains("type=2")) {
                //QQ好友
                ShareUtil.share2QQ(CommonWebActivity.this, shareParams, 2, mBaseUiListener);
            } else if (shareParams.contains("type=3")) {
                //QQ空间
                ShareUtil.share2QQ(CommonWebActivity.this, shareParams, 3, mBaseUiListener);
            }
        }

        @JavascriptInterface
        public void appFromVip(String params) {
            url = BuildConfig.SERVER_PATH + params + "&memberId=" + UserInfoUtils.getMemberId();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putString("title", "付款确认");
            CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
        }


        @JavascriptInterface
        public void appPay(String payParams) {
            if (payParams.contains("payType=1")) {
                //微信
                GiftsAPI.getInstance().getWxpayInfo(wxPayCallback, payParams + "&appType=3");
            } else {
                GiftsAPI.getInstance().getAlipayInfo(aliPayCallback, payParams + "&appType=3");
            }
        }


        @JavascriptInterface
        public void appIndex(String url) {
            CommonUtils.launchActivity(mContext, MainV2Activity.class, "index", 0);
            finish();
        }

        @JavascriptInterface
        public void appShopIndex() {
            CommonUtils.launchActivity(mContext, MainV2Activity.class, "index", 1);
            finish();
        }


        /**
         * 微信提现
         */
        @JavascriptInterface
        public void appWxDraw(String params) {
            wxIntentUrl = BuildConfig.SERVER_PATH + params;
            // send oauth request
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            App.sApi.sendReq(req);
        }


        @JavascriptInterface
        public void appCancleInvitate(String params) {
            Logger.d(params);
            UserAPI.getInstance().cancelInvitate(new ResultCallback<BasePOJO>() {
                @Override
                public void onResponse(BasePOJO response) {
                    super.onResponse(response);
                    if (response.isResultSuccess()) {
                        CCToast.showShort(mContext, "取消成功");
                        CommonUtils.launchActivity(mContext, MainV2Activity.class);
                        finish();
                    } else {
                        CCToast.showShort(mContext, "取消失败");
                    }
                }

                @Override
                public void onError(Request request, Exception e) {
                    super.onError(request, e);
                }
            }, params + "&appType=3");


        }


    }


    ResultCallback<WxPayInfo> wxPayCallback = new ResultCallback<WxPayInfo>() {
        @Override
        public void onResponse(WxPayInfo response) {
            super.onResponse(response);
            //TODO 进行微信支付
            PayReq req = new PayReq();
            if (response != null) {
                req.appId = response.getData().getAppid();
                req.partnerId = response.getData().getPartnerid();
                req.prepayId = response.getData().getPrepayid();
                req.nonceStr = response.getData().getNoncestr();
                req.timeStamp = response.getData().getTimestamp();
                req.packageValue = response.getData().getPackages();
                req.sign = response.getData().getSign();
                req.extData = "app data"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                App.sApi.sendReq(req);
            }
        }

        @Override
        public void onError(Request request, Exception e) {
            super.onError(request, e);
        }
    };


    ResultCallback<AlipayInfo> aliPayCallback = new ResultCallback<AlipayInfo>() {
        @Override
        public void onResponse(AlipayInfo response) {
            super.onResponse(response);
            AliPayUtil.aliPay(CommonWebActivity.this, response.getData(), mHandler);

        }

        @Override
        public void onError(Request request, Exception e) {
            super.onError(request, e);
        }
    };


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AliPayUtil.SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功",
                                Toast.LENGTH_SHORT).show();

                        //付款成功,跳转到首页
                        if (currentTitle.contains("充值")) {
                            rechargeOkDialog();
                        } else {
                            showPayResultDialog();
                        }

                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (App.wxPayStatus == 999) {
            //支付成功
            if (currentTitle.contains("充值")) {
                rechargeOkDialog();
            } else {
                showPayResultDialog();
            }
            App.wxPayStatus = 0;
        }

        if (App.wxOauthStatus == 100) {
            //授权成功
            wxIntentUrl = wxIntentUrl + "&wxOpenId=" + App.wxOpenId;
            Bundle bundle = new Bundle();
            bundle.putString("url", wxIntentUrl);
            bundle.putString("title", "申请提现");
            CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
            App.wxOauthStatus = 0;
            finish();
        }


    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        close();
    }

    public static void synCookies(Context context, String url) {
        //CookieSyncManager负责管理webView中的cookie
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        List<HttpCookie> cookies = App.getInstance().getMyCookieStore().getCookies(); //获取okhttp网络请求中持久化的cookie
        for (int i = 0; i < cookies.size(); i++) {
            HttpCookie cookie = cookies.get(i);
            if (cookie.getName().equals("PHPSESSID")) {
                // cookies是在HttpClient中获得的cookie，这里是从okhttp中获得，加入到webView的cookie中
                cookieManager.setCookie(url, cookie.getName() + "=" + cookie.getValue());
            }
        }
        CookieSyncManager.getInstance().sync();
    }


    private void close() {
        //清除WebView中cookie
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        //清除okhttp中cookie
        App.getInstance().clearCookies(this);
        //关闭WebActivity
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mBaseUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mBaseUiListener);
            }
        }


        switch (requestCode) {
            case FILE_SELECT_CODE:
                Uri uri = data.getData();
                Log.e("Tag", "Path:" + uri.toString());
                mUploadMessage.onReceiveValue(uri);
                mUploadMessage = null;
                break;
            case FILECHOOSER_RESULTCODE_FOR_ANDROID_5:
                if (null == mUploadMessageForAndroid5)
                    return;
                Uri result = (data == null || resultCode != RESULT_OK) ? null : data.getData();
                if (result != null) {
                    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
                } else {
                    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
                }
                mUploadMessageForAndroid5 = null;
                break;
        }


    }


    View.OnClickListener onExitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ACache.get(mContext).put(CcConst.KEY_USERINFO, "");
            CommonUtils.launchActivity(mContext, LoginV2Activity.class);
            finishAll();
        }
    };


    View.OnClickListener onRecordClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //被约记录
            url = BuildConfig.SERVER_PATH + "mobileMember_myFilmAllOrderList.do?memberId=" + UserInfoUtils.getMemberId();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putString("title", "被约记录");
            CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
        }
    };


    View.OnClickListener onJiuRecordClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //被约记录   mobileMember_myBarBeOredrList.do?memberId=''&appType=''
            url = BuildConfig.SERVER_PATH + "mobileMember_myBarBeOredrList.do?appType=3&memberId=" + UserInfoUtils.getMemberId();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            bundle.putString("title", "被约记录");
            CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
        }
    };


    private void showPayResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("付款成功");
        builder.setMessage("回到首页");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Bundle bundle = new Bundle();
//                bundle.putString("url", BuildConfig.SERVER_PATH + "invitation_invitationView.do?appType=3&memberId=" + UserInfoUtils.getMemberId());
//                bundle.putString("title", "约票");
//                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
                CommonUtils.launchActivity(mContext, MainV2Activity.class, "index", 1);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                CommonUtils.launchActivity(mContext, MainV2Activity.class, "index", 1);
            }
        });

        builder.show();
    }


    /**
     * 充值成功
     */
    private void rechargeOkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("充值成功");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                webView.goBack();
            }
        });


        builder.show();
    }

    public static void openWebActivity(Context ctx, String title, String url, int titleColor) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", title);
        bundle.putInt("titleColor", titleColor);
        CommonUtils.launchActivity(ctx, CommonWebActivity.class, bundle);
    }


}
