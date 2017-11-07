package live.cnaidai.com.ccyb.giftmarket;

import com.squareup.okhttp.Request;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.giftmarket.pojo.GiftsPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.util.ACache;
import live.cnaidai.com.ccyb.util.GsonUtil;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页P
 * @date 2016/9/19 10:45
 */

public class GiftMarketPresenter extends GiftMarketContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    public void getGifts(String pageSize) {
        mModel.getGifts(new ResultCallback<GiftsPOJO>() {
            @Override
            public void onResponse(GiftsPOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    //设置礼品清单
                    mView.setGifts(response.getData().getTicketList());
                    mView.setBars(response.getData().getBarList());
                    //本地缓存
                    String gifts = GsonUtil.convertObjToJson(response);
                    ACache.get(App.getContext()).put("gifts", gifts);

                } else {
                    response.showMsg();
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }
        }, pageSize);


    }
}
