package live.cnaidai.com.ccyb.member;

import live.cnaidai.com.ccyb.api.UserAPI;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页M
 * @date 2016/9/19 10:44
 */

public class MemberModel implements MemberContract.Model{


    @Override
    public void getUserData(ResultCallback<UserInfoPOJO> callback) {
        UserAPI.getInstance().getUserData(callback);
    }
}
