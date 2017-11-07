package live.cnaidai.com.ccyb.member;

import com.squareup.okhttp.Request;

import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home
 * @Description: 主页P
 * @date 2016/9/19 10:45
 */

public class MemberPresenter extends MemberContract.Presenter{

	@Override
	public void onStart() {

	}


	@Override
	public void getMemberData() {
		mModel.getUserData(new ResultCallback<UserInfoPOJO>() {
			@Override
			public void onResponse(UserInfoPOJO response) {
				super.onResponse(response);
				if(response!=null && response.isResultSuccess()){
					mView.setUserData(response.getData());
				}else{
//					response.showMsg();
				}
			}

			@Override
			public void onError(Request request, Exception e) {
				super.onError(request, e);
			}
		});
	}
}
