package live.cnaidai.com.ccyb.util;

import live.cnaidai.com.ccyb.BuildConfig;

/**
 * Created by ChristLu on 16/11/19.
 */

public class CcConst {
    public static final String KEY_USERINFO = "key_userinfo";

    public static final String KEY_USE_FIRST = "key_use_first";

    //我的约票
    public static final String URL_YUEPIAO = BuildConfig.SERVER_PATH + "mobileMember_myFilmOrderList.do";

    //我的礼包
    public static final String URL_GIFT = BuildConfig.SERVER_PATH + "mobileMember_myGiftOrderList.do";

    //会员特权
    public static final String URL_VIP = BuildConfig.SERVER_PATH + "mobileMember_toShowVIPService.do";

    //商品详情
    public static final String URL_GOOD = BuildConfig.SERVER_PATH + "giftShop_showGiftDetail.do";

    //商品搜索
    public static final String URL_GOOD_SEARCH = BuildConfig.SERVER_PATH + "giftShop_toShowQueryGift.do";

    //我的账户  http://139.196.112.56:8038/Ticket/mobileMember_showMyAccount.do
    public static final String URL_ACCOUNT = BuildConfig.SERVER_PATH + "mobileMember_showMyAccount.do";

    //我的提成  http://139.196.112.56:8038/Ticket/mobileMember_showMyCommission.do
    public static final String URL_INCOME = BuildConfig.SERVER_PATH + "mobileMember_showMyCommission.do";


    //我的提成  http://139.196.112.56:8038/Ticket/mobileMember_showMyIndex.do
    public static final String URL_MEMBER_INFO = BuildConfig.SERVER_PATH + "mobileMember_showMyIndex.do";


}
