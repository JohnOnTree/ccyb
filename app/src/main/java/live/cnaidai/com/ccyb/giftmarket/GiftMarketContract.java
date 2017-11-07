package live.cnaidai.com.ccyb.giftmarket;


import java.util.List;

import live.cnaidai.com.ccyb.base.BaseModel;
import live.cnaidai.com.ccyb.base.BasePresenter;
import live.cnaidai.com.ccyb.base.BaseView;
import live.cnaidai.com.ccyb.giftmarket.pojo.GiftsPOJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;


/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页Contract
 * @date 2016/9/19 10:44
 */


interface GiftMarketContract {

    interface Model extends BaseModel {
        void getGifts(ResultCallback<GiftsPOJO> callback, String pagesIndex);
    }

    interface View extends BaseView {
        void setGifts(List<HomeDataPOJO.DataEntity.IndexGiftListEntity> giftDatas);
        void setBars(List<GiftsPOJO.DataEntity.BarListEntity> giftDatas);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getGifts(String pageSize);
    }

}
