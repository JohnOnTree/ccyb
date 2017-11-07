package live.cnaidai.com.ccyb.home;


import java.util.List;

import live.cnaidai.com.ccyb.base.BaseModel;
import live.cnaidai.com.ccyb.base.BasePresenter;
import live.cnaidai.com.ccyb.base.BaseView;
import live.cnaidai.com.ccyb.home.V3.HomeDataV3POJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;


/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页Contract
 * @date 2016/9/19 10:44
 */


public  interface HomeContract {

    interface Model extends BaseModel {
        void getHomeAd(ResultCallback<HomeDataPOJO> callback, String pagesIndex);
        void getUsers(ResultCallback<UsersPOJO> callback, String pagesIndex, String memberId);
        void getHomeDatas(ResultCallback<HomeDataV3POJO> callback, String pagesIndex);


    }

    interface View extends BaseView {
        void setAdDatas(List<HomeDataPOJO.DataEntity.IndexAdvertListEntity> adDatas);
        void setGifts(List<HomeDataPOJO.DataEntity.IndexGiftListEntity> giftDatas);
        void setAds(List<UsersPOJO.DataEntity.IndexAdvertListEntity> ads);
        void setUsers(List<UsersPOJO.DataEntity.MemberListEntity> users);
        void setDatas(HomeDataV3POJO v3POJO);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getHomeAd(String pageSize);
        public abstract void getUsers(String pageSize,String memberId);
        public abstract void getHomeDatas(String pageSize);


    }

}
