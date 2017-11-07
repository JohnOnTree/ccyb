package live.cnaidai.com.ccyb.api;


import java.util.HashMap;
import java.util.Map;

import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.st.pojo.PraisePOJO;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;
import live.cnaidai.com.ccyb.util.UserInfoUtils;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.api
 * @Description: 用户操作
 * @date 2016/9/27 11:35
 */

public class StAPI extends BaseAPI {

    private static StAPI sUserAPI;

    public static StAPI getInstance() {
        if (sUserAPI == null) {
            sUserAPI = new StAPI();
        }
        return sUserAPI;
    }

    /**
     * 晒图首页
     */
    private static final String URL_LIST_ST = BuildConfig.SERVER_PATH + "app/common/Common2Action_demoIndexList.do";
    /**
     * 点赞
     */
    private static final String URL_PRAISE = BuildConfig.SERVER_PATH + "app/common/Common2Action_praiseOperate.do";


    public void getStList(ResultCallback<StPOJO> callback, String page) {
        Map<String, String> map = new HashMap<>();
        map.put("pagesIndex", page);
        map.put("memberId", UserInfoUtils.getMemberId());
        exeutePostMethods(callback, URL_LIST_ST, map);
    }

    /**
     * @param callback
     */
    public void praise(ResultCallback<PraisePOJO> callback, String demoId, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("demoId", demoId);
        map.put("type", type);
        map.put("memberId", UserInfoUtils.getMemberId());
        exeutePostMethods(callback, URL_PRAISE, map);
    }


}
