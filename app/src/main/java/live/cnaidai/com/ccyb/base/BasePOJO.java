package live.cnaidai.com.ccyb.base;

import java.io.Serializable;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.util.CCToast;

/**
 * @author JokerX
 * @version V1.0
 * @Package com.example.jokerx.mvpsimple.base
 * @Description: 基础的实体
 * @date 2016/5/16 10:44
 */
public class BasePOJO implements Serializable{


	private int result;

	private String message ;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public boolean isResultSuccess(){
		return result == 0 ;
	}

	public void showMsg(){
		CCToast.showShort(App.getContext(),message);
	}


}
