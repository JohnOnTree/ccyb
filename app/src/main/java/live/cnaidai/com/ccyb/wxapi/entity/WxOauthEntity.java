package live.cnaidai.com.ccyb.wxapi.entity;

import java.io.Serializable;

/**
 * description:
 * autour: ChristLu
 * date: 17/7/22
 * package: live.cnaidai.com.ccyb.wxapi.entity
 */
public class WxOauthEntity implements Serializable{

    /**
     * access_token : _blRhY9zWUdu482SIkO22SLFjDuhD0BIYXIqjasN02WO-aMkeBijd_jPVjwomWh1HfLgdZiBjyU8N9VNbe-OVDlViU2MPpIj4Fqsv0AKW70
     * expires_in : 7200
     * refresh_token : KTZgtsT13b1w6teBNzJ7ZveeqqIK7Do50GKRDjscwAYrdtuEKMkoRsxICupmRzpYBPr_c7CxB4vsir3zbyZAINC_OqsDwYVdaXfjI4XrUKI
     * openid : oIaP4v3e5CLLAoSqCsCxBJVL6SgM
     * scope : snsapi_userinfo
     * unionid : oK3JXwSCLSYb8KoC69bjJvlnbQf0
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
