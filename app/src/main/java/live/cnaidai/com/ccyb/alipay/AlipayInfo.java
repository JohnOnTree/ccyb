package live.cnaidai.com.ccyb.alipay;


import java.io.Serializable;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * Created by ChristLu on 16/8/22.
 */

public class AlipayInfo extends BasePOJO {


    /**
     * body : 从从充值订单
     * privateKey : MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDNWhS8NAlPSAFmP5Byy6ADtwTvSrm0z8xuYzPUIXBIebhmX5yx6e/7SboTPIPXvbdjNhq14n866EqWOu8EbkUnYnIDioiHJZrnLMmzw0zf80oh0LDmCEGg87sLOyuZMqYiT6jwLNA3oKPjwygm1+4/c7RN/K/Hzl7UPERRa3HPFhMgzKhlnax+3KXkxaNQI9er8DcfPtqQn4bF3CGmVbBL0QLLA7nLMBpEdIDgNeKoeCWOCA6RVNVYjA+RQNBI1BqtF2cFiKuKW7+o75RzNIlkoaxgHgr/1O3LeUEoLtbLPB2qZE//ek34ZjHUNs86Y8R3vZS+bxkrUqhxTrvpplKTAgMBAAECggEBALMYHj9pzrjDXt0TEOLdbmfvBbHA5Sepl2yqvjgDFc9PwSAfwSR1l6P8T/mY7I+K2VcAh9kbecUhdHODYJPnrA5GUJOADcR4apm1uRA7g19J6tyFCtMEXmCGURnheAu7XaNNAFS/cLUjN+xbdzgxWIskWRsvBwtmk9Pb3+V2pKJq2T4EUI9//zioXOd9/C15EldpYZLOKp+YFlOasWNqhtTLK7uPBCY5I/5j7i+Ui4IFP0YaIz8WkVmUBiPgnQLeIT/2f9/TV2ZkykRrUQTENaVxTOUt12WOFEcqK/e6HGnLVrLFrZrgbxiHYEQLg6uK1+l2DcoHhS7yTAIUlEC3zbkCgYEA53nAuxm+y1Fm+F6n86HVTkvWBHX/+40a8knP05kzluRskx8VYuJUqWWEggDbSlbGx5Yqd1Nj+tsv3mNDIGmgVCuHd672UGusiaANmAO0OsgIFEANPGkTRGupb5chrzNlJJDEFUExvfmfOag8OQK3vyrN80OSPujjkGEKR3tJg3cCgYEA4xvILBLGlBDomoncXhmtmhfKuFQMnbZrZ47lKM8PhQoGfTZmy82Y7IWOm8UuMgJ+YxKVtkQnZTxzSn/P/UdF7GES0DaAesu3cd4FiZ1D9zG73qOG2sPlqMJyypyA6TYn/nX5dxgIA0g035XUWOfZ8NSkY4Xm24LyHX6n8D6HGMUCgYEAq/VI9jXEBWrtoek/v8AjpEy2Kw42InvK3t9yovEW4CvFdO4Mhy84Ubd23tUgE3OvrCmkVoz+oycdwonfHj8Zui20L3csyMWUoZDjSGDIDTr4tYJ30r8EbznD34BYp7z4kFdzM48BEu5UGRMf2gJo2n1EDknOZSnBlEpixoOSMzUCgYACP78uuIzg4H39McpN4EE42m12f9aUJT9RCjg4vDwyHBROXyCLeiC+juAHqN85PY8U2hVDdc9SEFQv864SmwcVPSzbhP6muKe7Z/B0GWuX4hUzMjlepzv5/Nr4KK6aIKAqw+5RgJprteZYJHqy2rDs0p5CIgxUNirBogR1u5jI5QKBgQDi9ABpj9DeLXZD/rvteUUhzqoRXhG7Nz/8XRtyqMHvRDPXJ1533VX8tfA6eqYAhikuN0VHbYPryHZWb4dnYAL00RxZUgh4OmZ526gsVtB37IAs87PMtLY3sbMAxWq/rWvJcLFxJc9Na0L2BjE6n2ckIybmrj8bOZ0TwOTrR2Ck8w==
     * total_fee : 0.01
     * subject : 从从充值订单
     * notify_url : http://139.196.112.56:8038/Ticket/app/PayNotify?method=aliPayNodify&type=1
     * service : mobile.securitypay.pay
     * payType : 2
     * partner : 2088521367246723
     * seller_email : xh56685336@sina.com
     * out_trade_no : 2017072211470751080
     * payment_type : 1
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        private String body;
        private String privateKey;
        private String total_fee;
        private String subject;
        private String notify_url;
        private String service;
        private String payType;
        private String partner;
        private String seller_email;
        private String out_trade_no;
        private String payment_type;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getSeller_email() {
            return seller_email;
        }

        public void setSeller_email(String seller_email) {
            this.seller_email = seller_email;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }
    }
}
