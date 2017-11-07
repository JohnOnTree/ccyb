package live.cnaidai.com.ccyb.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import live.cnaidai.com.ccyb.util.TUtil;


/**
 * @author JokerX
 * @version V1.0
 * @Package com.aidai.aidaimvp.base
 * @Description: Fragment基类
 * @date 2016/5/18 10:03
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements BaseView{

	public T mPresenter;
	public E mModel;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPresenter = TUtil.getT(this, 0);
		mModel = TUtil.getT(this, 1);
		this.initPresenter();
		initContent();
	}

	private void initContent() {
	}

	/**
	 * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	 */
	public abstract void initPresenter();

	/**
	 * 显示加载进度框
	 */
	public void showLoadDialog() {
	}

	/**
	 * 隐藏加载进度框
	 */
	public void hideLoadDialog() {
	}

	@Override
	public void showLoading() {
		showLoadDialog();
	}

	@Override
	public void hideLoading() {
		hideLoadDialog();
	}

}
