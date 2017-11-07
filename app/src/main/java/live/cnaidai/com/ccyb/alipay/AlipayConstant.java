package live.cnaidai.com.ccyb.alipay;


public class AlipayConstant {
	// 商户PID
	public static final String PARTNER = "2088001133171935";
	// 商户收款账号
	public static final String SELLER = "reqesoft@reqe.cn";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKUyt1OdHVI/frdTfbXteWANQhmFc/VPhzBuOH2W5jWXCkBK/lpgcH/XoCyekE60hfqUwj6ER8IJ6h3yBoRnF0jeaEo9wFN1nXazK4C7JICtFxLwd0QVrkwxEDF0TBv5iM1GBOeXNRvw6CJMHKLdAbcQJfAHBiGSma5bSNsSu41RAgMBAAECgYAmlYjiG6E8k536HaMP+e5Oc+pLYbMqya9urV++XZ9ZRvTa/WkZHMtJ4MgFg+d6qolcdKBFz8me1Yf8BIpnJFj4iO9xEm1tP4MxSK2rMUjAM9WeJNAzpd82U1EPI5gAunoQ0+DAQ2Gcs056dYlE7YLFYNLY+a7gHjfWoBK6QspEPQJBANWmXQt69Wpb4H9momRrIh36H+EpBAoyvYHutvRAshoLE6RAn/Lqw08bc1mrlDC6B0cFCL8BAdAR2FcsZCLorWMCQQDF8aytCUg4PMYE9VI5BcZfyT6L4TddGZUqELJD2edufOrRuPjIvCMy8ojZQl8wErjwz8sopcgfYpOjdINJl2K7AkEAyf0nKpttOsjyToCyN04ySzUWvsEQWLPwBs+xmOsxdv3R7BBNKFRboobJX5Wf3RGDn9lak3pYRGcaqhAF/BsPEQJBAJ9i6ZrvhXKbJQvrCckWqFSKtE1oDO9vYRfdF93gwSTLez1iRxlDvMgSWTUv53vLJ5Xoi/rv+sOi2aTddbW1K28CQBONJ78N/MqPZl35SXdjH72ESNjTtGN9sdveyGoepSGZb0rRltN2S6xP1oH4Cq4C6Sj8hrDmiCP8eqL9+dXvm+s=";
	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	private static final int SDK_PAY_FLAG = 1;

	private String url ;

	public AlipayConstant(){

	}


	public AlipayConstant(String PARTNER, String SELLER, String url, String rsa_key){
		this.url = url ;

	}
	

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String body, String price, String orderNo) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + url
				+ "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}
}
