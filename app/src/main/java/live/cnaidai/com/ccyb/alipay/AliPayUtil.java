package live.cnaidai.com.ccyb.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.pay
 * @Description: 支付宝支付工具类
 * @date 2016/9/26 11:48
 */

public class AliPayUtil {

	//AiliPay
	public static final int SDK_PAY_FLAG = 1;

	public interface PayResultCallback {
		void onPaySuccess(String msg);

		void onPayFail(String err);
	}

	public static void aliPay(final Activity ctx , AlipayInfo.DataEntity alipayInfo, final Handler mHandler){
		AlipayConstant aliPayConstant = new AlipayConstant(alipayInfo.getPartner(),alipayInfo.getSeller_email(),alipayInfo.getNotify_url(),alipayInfo.getPrivateKey());
		String orderInfo = aliPayConstant.getOrderInfo(alipayInfo.getSubject(), alipayInfo.getBody(), alipayInfo.getTotal_fee(),
				alipayInfo.getOut_trade_no());
		/**
		 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
		 */
		String sign = aliPayConstant.sign(orderInfo);
		try {
			/**
			 * 仅需对sign 做URL编码
			 */
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 * 完整的符合支付宝参数规范的订单信息
		 */
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ aliPayConstant.getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(ctx);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();

	}









}
