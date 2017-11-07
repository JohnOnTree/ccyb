package live.cnaidai.com.ccyb.user;


import live.cnaidai.com.ccyb.base.BaseModel;
import live.cnaidai.com.ccyb.base.BasePresenter;
import live.cnaidai.com.ccyb.base.BaseView;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;
import live.cnaidai.com.ccyb.user.pojo.VcodePOJO;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.user.login
 * @Description: 登陆控制类
 * @date 2016/9/13 16:26
 */

public interface LoginContract {

    /**
     * 登陆M的创建
     */
    interface Model extends BaseModel {
        void getVcode(ResultCallback<VcodePOJO> callback,String phone);
        void login(ResultCallback<UserInfoPOJO> callback, String phone,String mInviteCode,String password);
        void loginV2(ResultCallback<UserInfoPOJO> callback, String phone,String password);
    }

    /**
     * 登陆V的创建
     */
    interface View extends BaseView {
        void startTimeCount();
        void loginSuccess();
        void cancelTimer();
    }

    /**
     * 登陆界面中所需要的操作
     */
    abstract class Presenter extends BasePresenter<Model, View> {
        abstract  void setSoftKeyBoard(android.view.View main,  android.view.View scroll);
        abstract  void getVcode(String phone);
        abstract void login(String phone,String vCode,String mInviteCode,String password);
        abstract void loginV2(String phone,String password);



        @Override
        public void onStart() {

        }


    }
}
