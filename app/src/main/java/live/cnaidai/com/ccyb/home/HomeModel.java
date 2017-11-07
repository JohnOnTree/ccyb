package live.cnaidai.com.ccyb.home;

import live.cnaidai.com.ccyb.api.HomeAPI;
import live.cnaidai.com.ccyb.home.V3.HomeDataV3POJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页M
 * @date 2016/9/19 10:44
 */

public class HomeModel implements HomeContract.Model{

    @Override
    public void getHomeAd(ResultCallback<HomeDataPOJO> callback, String pagesIndex) {
        HomeAPI.getInstance().getHomeData(callback,pagesIndex);
    }

    @Override
    public void getUsers(ResultCallback<UsersPOJO> callback, String pagesIndex,String memberId) {
        HomeAPI.getInstance().getUsers(callback,pagesIndex,memberId);

    }

    @Override
    public void getHomeDatas(ResultCallback<HomeDataV3POJO> callback, String pagesIndex) {
        HomeAPI.getInstance().getHomeDatas(callback,pagesIndex);
    }


}
