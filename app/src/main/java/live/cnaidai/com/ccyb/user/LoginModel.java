package live.cnaidai.com.ccyb.user;

import live.cnaidai.com.ccyb.api.UserAPI;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;
import live.cnaidai.com.ccyb.user.pojo.VcodePOJO;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.user.login
 * @Description: 登陆
 * @date 2016/9/13 16:37
 */

public class LoginModel implements LoginContract.Model{


    @Override
    public void getVcode(ResultCallback<VcodePOJO> callback, String phone) {
        UserAPI.getInstance().getVcode(callback,phone);
    }

    @Override
    public void login(ResultCallback<UserInfoPOJO> callback, String phone,String mInviteCode,String passowrd) {
        UserAPI.getInstance().login(callback,phone,mInviteCode,passowrd);
    }

    @Override
    public void loginV2(ResultCallback<UserInfoPOJO> callback, String phone, String password) {
        UserAPI.getInstance().loginV2(callback,phone,password);

    }


}
