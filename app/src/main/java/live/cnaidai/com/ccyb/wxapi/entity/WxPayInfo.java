package live.cnaidai.com.ccyb.wxapi.entity;

import java.io.Serializable;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * Created by ChristLu on 17/1/4.
 */

public class WxPayInfo extends BasePOJO {


    /**
     * sign : E68B910E41E954676D4D432EEA3A5991
     * timestamp : 1500694583
     * noncestr : 2roHGttmxUgABZDv
     * partnerid : 1433468102
     * packages : Sign=WXPay
     * prepayid : wx201707221136235fd6e5d2f50171799428
     * payType : 1
     * appid : wx08f2026572bb6477
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        private String sign;
        private String timestamp;
        private String noncestr;
        private String partnerid;
        private String packages;
        private String prepayid;
        private String payType;
        private String appid;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPackages() {
            return packages;
        }

        public void setPackages(String packages) {
            this.packages = packages;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }
}
