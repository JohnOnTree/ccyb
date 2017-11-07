package live.cnaidai.com.ccyb.api;


import java.util.Map;

import live.cnaidai.com.ccyb.net.OkHttpClientManager;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;

/**
 * Created by ChristLu on 2015/12/21.
 */
public class BaseAPI {


    public void exeutePostMethods(ResultCallback<?> callBack, String url, Map<String, String> params) {
        OkHttpClientManager.postAsyn(url, callBack, params);
    }

    public void exeuteGetMethods(ResultCallback<?> callBack, String url) {
        OkHttpClientManager.getAsyn(url, callBack);
    }

}
