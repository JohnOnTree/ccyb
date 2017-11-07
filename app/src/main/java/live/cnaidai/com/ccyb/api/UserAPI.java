package live.cnaidai.com.ccyb.api;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.base.BasePOJO;
import live.cnaidai.com.ccyb.net.OkHttpClientManager;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;
import live.cnaidai.com.ccyb.user.pojo.VcodePOJO;
import live.cnaidai.com.ccyb.util.UserInfoUtils;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.api
 * @Description: 用户操作
 * @date 2016/9/27 11:35
 */

public class UserAPI extends BaseAPI {

    private static UserAPI sUserAPI;

    public static UserAPI getInstance() {
        if (sUserAPI == null) {
            sUserAPI = new UserAPI();
        }
        return sUserAPI;
    }

    /**
     * 获得验证码
     */
    private static final String URL_VCODE_GET = BuildConfig.SERVER_PATH + "app/common/CommonAction_sendSmsMessage.do";
    /**
     * 注册
     */
    private static final String URL_REGISTER = BuildConfig.SERVER_PATH + "app/common/CommonAction_memberRegister.do";


    /**
     * 登录
     */
    private static final String URL_LOGINV2 = BuildConfig.SERVER_PATH + "app/common/CommonAction_memberLogin.do";

    /**
     * 获取用户数据
     */
    private static final String URL_USERDATA_GET = BuildConfig.SERVER_PATH + "app/common/CommonAction_memberCenter.do";
    /**
     * 上传经纬度
     */
    private static final String URL_LOCATION_UPLOAD = BuildConfig.SERVER_PATH + "app/common/CommonAction_memberLocation.do";


    public static final String URL_IMG_UPLOAD = BuildConfig.SERVER_PATH + "FileImageUploadServlet";


    /**
     * 删除图片
     */
    private static final String URL_PIC_DEL = BuildConfig.SERVER_PATH + "app/common/CommonAction_delPic.do";


    /**
     * 认证发送短信验证码
     *
     * @param callback
     * @param memberPhone
     */
    public void getVcode(ResultCallback<VcodePOJO> callback, String memberPhone) {
        Map<String, String> map = new HashMap<>();
        map.put("memberPhone", memberPhone);
        exeutePostMethods(callback, URL_VCODE_GET, map);
    }

    /**
     * 登录
     *
     * @param callback
     * @param memberPhone
     */
    public void login(ResultCallback<UserInfoPOJO> callback, String memberPhone, String mInviteCode, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("memberPhone", memberPhone);
        map.put("password", password);
        map.put("deviceType", "3");
        map.put("shareCodeParent", mInviteCode);
        map.put("lon", App.longitude + "");
        map.put("lat", App.latitude + "");
        map.put("regionId", App.sRegistrationId);
        exeutePostMethods(callback, URL_REGISTER, map);
    }

    /**
     * 登录
     *
     * @param callback
     * @param memberPhone
     */
    public void loginV2(ResultCallback<UserInfoPOJO> callback, String memberPhone, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("memberPhone", memberPhone);
        map.put("deviceType", "3");
        map.put("password", password);
        map.put("lon", App.longitude + "");
        map.put("lat", App.latitude + "");
        map.put("regionId", App.sRegistrationId);
        exeutePostMethods(callback, URL_LOGINV2, map);
    }

    /**
     * 获取用户数据
     *
     * @param callback
     */
    public void getUserData(ResultCallback<UserInfoPOJO> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", UserInfoUtils.getMemberId());
        map.put("appType", 3 + "");
        exeutePostMethods(callback, URL_USERDATA_GET, map);
    }


    public void uploadLocation() {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", UserInfoUtils.getMemberId());
        map.put("lon", App.longitude + "");
        map.put("lat", App.latitude + "");
        exeutePostMethods(new ResultCallback<BasePOJO>() {

        }, URL_LOCATION_UPLOAD, map);
    }


    /**
     * 取消约票
     *
     * @param callback
     */
    public void cancelInvitate(ResultCallback<BasePOJO> callback, String params) {
        exeuteGetMethods(callback, BuildConfig.SERVER_PATH + params);
    }


    public void uploadImg(ResultCallback<BasePOJO> callback, File file) {
        OkHttpClientManager.Param param2 = new OkHttpClientManager.Param("memberId", UserInfoUtils.getMemberId());
        OkHttpClientManager.Param[] params = new OkHttpClientManager.Param[1];
        params[0] = param2;
        try {
            OkHttpClientManager.postAsyn(URL_IMG_UPLOAD, callback, file, "photo", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除图片
     * @param callback
     * @param picId
     */
    public void delImage(ResultCallback<BasePOJO> callback, String picId) {
        Map<String, String> map = new HashMap<>();
        map.put("picId", picId);
        exeutePostMethods(callback, URL_PIC_DEL, map);
    }


}
