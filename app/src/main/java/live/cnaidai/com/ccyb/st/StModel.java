package live.cnaidai.com.ccyb.st;

import live.cnaidai.com.ccyb.api.StAPI;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页M
 * @date 2016/9/19 10:44
 */

public class StModel implements StContract.Model {

    @Override
    public void getStDatas(ResultCallback<StPOJO> callback, String pagesIndex) {
        StAPI.getInstance().getStList(callback, pagesIndex);
    }

}
