package live.cnaidai.com.ccyb.base;

import android.content.Context;

/**
 * @author JokerX
 * @version V1.0
 * @Package com.aidai.aidaimvp.base
 * @Description: 基础的Presenter(使用泛型解耦)
 * @date 2016/5/18 10:47
 */
public abstract class BasePresenter<E, T> {

	public Context context;
	public E mModel;
	public T mView;
	public RxManager mRxManage = new RxManager();

	public void setVM(T v, E m) {
		this.mView = v;
		this.mModel = m;
		this.onStart();
	}

	public abstract void onStart();

	public void onDestroy() {
		mRxManage.clear();
	}
}
