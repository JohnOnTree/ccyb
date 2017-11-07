package live.cnaidai.com.ccyb.giftmarket;

import live.cnaidai.com.ccyb.api.GiftsAPI;
import live.cnaidai.com.ccyb.giftmarket.pojo.GiftsPOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页M
 * @date 2016/9/19 10:44
 */

public class GiftMarketModel implements GiftMarketContract.Model{

    @Override
    public void getGifts(ResultCallback<GiftsPOJO> callback, String pagesIndex) {
        GiftsAPI.getInstance().getGifts(callback,pagesIndex);
    }
}
