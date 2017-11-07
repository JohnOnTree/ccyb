package live.cnaidai.com.ccyb.st;


import java.util.List;

import live.cnaidai.com.ccyb.base.BaseModel;
import live.cnaidai.com.ccyb.base.BasePresenter;
import live.cnaidai.com.ccyb.base.BaseView;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;

interface StContract {

    interface Model extends BaseModel {
        void getStDatas(ResultCallback<StPOJO> callback, String pagesIndex);

    }

    interface View extends BaseView {
        void setStDatas(List<StPOJO.DataEntity> datas);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getStDatas(int page);
    }

}
