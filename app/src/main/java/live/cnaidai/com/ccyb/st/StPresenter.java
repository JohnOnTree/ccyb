package live.cnaidai.com.ccyb.st;

import com.squareup.okhttp.Request;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;
import live.cnaidai.com.ccyb.util.CCToast;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页P
 * @date 2016/9/19 10:45
 */

public class StPresenter extends StContract.Presenter {

    @Override
    public void onStart() {

    }


    @Override
    public void getStDatas(int page) {
        mModel.getStDatas(new ResultCallback<StPOJO>() {
            @Override
            public void onResponse(StPOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    mView.setStDatas(response.getData());
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                CCToast.showShort(App.getContext(), "数据异常");
            }
        }, page + "");
    }
}
