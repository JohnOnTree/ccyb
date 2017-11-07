package live.cnaidai.com.ccyb.member;


import live.cnaidai.com.ccyb.base.BaseModel;
import live.cnaidai.com.ccyb.base.BasePresenter;
import live.cnaidai.com.ccyb.base.BaseView;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;


/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页Contract
 * @date 2016/9/19 10:44
 */


interface MemberContract {

    interface Model extends BaseModel {
        void getUserData(ResultCallback<UserInfoPOJO> callback);
    }

    interface View extends BaseView {
        void setUserData(UserInfoPOJO.DataEntity data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getMemberData();
    }
}
