package live.cnaidai.com.ccyb.home;

import com.squareup.okhttp.Request;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.home.V3.HomeDataV3POJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
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

public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    public void getHomeAd(String pageSize) {
        mModel.getHomeAd(new ResultCallback<HomeDataPOJO>() {
            @Override
            public void onResponse(HomeDataPOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    String jsonInfo = GsonUtil.convertObjToJson(response);
                    ACache.get(App.getContext()).put("homedata",jsonInfo);
                    //设置轮播图
                    mView.setAdDatas(response.getData().getIndexAdvertList());
                    //设置礼品清单
                    mView.setGifts(response.getData().getIndexGiftList());
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

    @Override
    public void getUsers(String pageSize,String memberId) {
        mModel.getUsers(new ResultCallback<UsersPOJO>() {
            @Override
            public void onResponse(UsersPOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    String jsonInfo = GsonUtil.convertObjToJson(response);
                    ACache.get(App.getContext()).put("users",jsonInfo);
                    //设置轮播图
                    mView.setAds(response.getData().getIndexAdvertList());
                    //设置礼品清单
                    mView.setUsers(response.getData().getMemberList());
                } else {
                    response.showMsg();
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }
        },pageSize,memberId);

    }

    @Override
    public void getHomeDatas(String pageSize) {
        mModel.getHomeDatas(new ResultCallback<HomeDataV3POJO>() {
            @Override
            public void onResponse(HomeDataV3POJO response) {
                super.onResponse(response);
                if(response.isResultSuccess()){
                    mView.setDatas(response);
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }
        },pageSize);
    }


}
