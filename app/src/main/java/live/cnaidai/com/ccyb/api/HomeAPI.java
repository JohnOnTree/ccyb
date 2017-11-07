package live.cnaidai.com.ccyb.api;


import java.util.HashMap;
import java.util.Map;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.home.V3.HomeDataV3POJO;
import live.cnaidai.com.ccyb.home.pojo.CanInvitePOJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.wxapi.entity.WxOauthEntity;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.api
 * @Description: 用户操作
 * @date 2016/9/27 11:35
 */

public class HomeAPI extends BaseAPI {

    private static HomeAPI sUserAPI;

    public static HomeAPI getInstance() {
        if (sUserAPI == null) {
            sUserAPI = new HomeAPI();
        }
        return sUserAPI;
    }

    /**
     * 获得首页数据
     */
    private static final String URL_HOMEDATA = BuildConfig.SERVER_PATH + "app/common/CommonAction_barIndexList.do";

    /**
     * 是否可邀约
     */
    private static final String URL_INVITE_JUDGE = BuildConfig.SERVER_PATH + "app/common/CommonAction_judgeInvite.do";

    /**
     * 获得首页数据
     *
     * @param callback
     * @param pagesIndex
     */
    public void getHomeData(ResultCallback<HomeDataPOJO> callback, String pagesIndex) {
        Map<String, String> map = new HashMap<>();
        map.put("pagesIndex", pagesIndex);
        exeutePostMethods(callback, URL_HOMEDATA, map);
    }


    /**
     * 获得首页数据
     *
     * @param callback
     * @param pagesIndex
     */
    public void getUsers(ResultCallback<UsersPOJO> callback, String pagesIndex, String memberId) {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("pagesIndex", pagesIndex);
        map.put("lon", App.longitude + "");
        map.put("lat", App.latitude + "");
        exeutePostMethods(callback, URL_HOMEDATA, map);
    }

    /**
     * 获得首页数据
     *
     * @param callback
     * @param pagesIndex
     */
    public void getHomeDatas(ResultCallback<HomeDataV3POJO> callback, String pagesIndex) {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", UserInfoUtils.getMemberId());
        map.put("pagesIndex", pagesIndex);
        map.put("lon", App.longitude + "");
        map.put("lat", App.latitude + "");
        exeutePostMethods(callback, URL_HOMEDATA, map);
    }

    /**
     * 判断是否可以邀约
     *
     * @param callback
     * @param memberId
     * @param choiceId
     */
    public void canInvite(ResultCallback<CanInvitePOJO> callback, String memberId, String choiceId) {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("choiceId", choiceId);
        exeutePostMethods(callback, URL_INVITE_JUDGE, map);
    }

    /**
     * 获得微信用户信息
     * @param callback
     * @param url
     */
    public void getWxUserInfo(ResultCallback<WxOauthEntity> callback, String url){
        exeuteGetMethods(callback,url);
    }


}
