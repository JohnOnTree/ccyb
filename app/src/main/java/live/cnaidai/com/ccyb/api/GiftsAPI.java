package live.cnaidai.com.ccyb.api;


import java.util.HashMap;
import java.util.Map;

import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.alipay.AlipayInfo;
import live.cnaidai.com.ccyb.giftmarket.pojo.GiftsPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.wxapi.entity.WxPayInfo;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.api
 * @Description: 用户操作
 * @date 2016/9/27 11:35
 */

public class GiftsAPI extends BaseAPI {

    private static GiftsAPI sUserAPI;

    public static GiftsAPI getInstance() {
        if (sUserAPI == null) {
            sUserAPI = new GiftsAPI();
        }
        return sUserAPI;
    }

    /**
     * 获得验证码
     */
    private static final String URL_GIFTS = BuildConfig.SERVER_PATH + "app/common/CommonAction_barShopList.do";

    /**
     * 搜索
     */
    private static final String URL_GIFTS_SEARCH = BuildConfig.SERVER_PATH + "app/common/CommonAction_doGiftQuery.do";
    /**
     * 充值
     */
    private static final String URL_RECHARGE = BuildConfig.SERVER_PATH + "mobliePay/pay_burseRecharge.do";


    /**
     * 获得礼品商城数据
     * @param callback
     * @param pagesIndex
     */
    public void getGifts(ResultCallback<GiftsPOJO> callback, String pagesIndex) {
        Map<String, String> map = new HashMap<>();
        map.put("pagesIndex", pagesIndex);
        exeutePostMethods(callback, URL_GIFTS, map);
    }


    /**
     * 搜索
     * @param callback
     * @param searchContent
     */
    public void search(ResultCallback<GiftsPOJO> callback, String searchContent) {
        Map<String, String> map = new HashMap<>();
        map.put("searchContent", searchContent);
        exeutePostMethods(callback, URL_GIFTS_SEARCH, map);
    }

    /**
     * 支付信息
     * @param callback
     * @param params
     */
    public void getAlipayInfo(ResultCallback<AlipayInfo> callback,String params){
        exeuteGetMethods(callback,BuildConfig.SERVER_PATH+params);
    }


    public void getWxpayInfo(ResultCallback<WxPayInfo> callback, String params){
        exeuteGetMethods(callback,BuildConfig.SERVER_PATH+params);
    }


    /**
     * 支付宝充值
     * @param callback
     * @param orderPrice
     */
    public void rechargeForAlipay(ResultCallback<AlipayInfo> callback,String orderPrice){
        Map<String, String> map = new HashMap<>();
        map.put("orderPrice", orderPrice);
        map.put("payType", "2");
        map.put("memberId", UserInfoUtils.getMemberId());
        exeutePostMethods(callback, URL_RECHARGE, map);
    }


    /**
     * 微信充值
     * @param callback
     * @param orderPrice
     */
    public void rechargeForWechat(ResultCallback<WxPayInfo> callback,String orderPrice){
        Map<String, String> map = new HashMap<>();
        map.put("orderPrice", orderPrice);
        map.put("payType", "1");
        map.put("memberId", UserInfoUtils.getMemberId());
        exeutePostMethods(callback, URL_RECHARGE, map);
    }







}
