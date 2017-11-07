package live.cnaidai.com.ccyb.util;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;

/**
 * Created by ChristLu on 16/11/19.
 */

public class UserInfoUtils {
    public static void saveUserInfo(UserInfoPOJO userInfoPOJO) {
        if (userInfoPOJO != null) {
            String userinfo = GsonUtil.convertObjToJson(userInfoPOJO);
            ACache.get(App.getContext()).put(CcConst.KEY_USERINFO, userinfo);
        }
    }

    public static String getMemberId() {
        String userInfo = ACache.get(App.getContext()).getAsString(CcConst.KEY_USERINFO);
        UserInfoPOJO userInfoPOJO = GsonUtil.convertJsonToObj(userInfo, UserInfoPOJO.class);
        if (userInfoPOJO != null&&userInfoPOJO.getData()!=null) {
            return userInfoPOJO.getData().getMemberId() + "";
        }
        return "0";
    }

    public static boolean isLogin() {
        return !"0".equals(getMemberId());
    }

}
