package live.cnaidai.com.ccyb.user;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.squareup.okhttp.Request;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;
import live.cnaidai.com.ccyb.user.pojo.VcodePOJO;
import live.cnaidai.com.ccyb.util.CCToast;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;

import static live.cnaidai.com.ccyb.App.vCode;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.user.login
 * @Description: 登陆
 * @date 2016/9/13 16:36
 */

public class LoginPresenter extends LoginContract.Presenter {


    @Override
    void setSoftKeyBoard(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > 50) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    main.scrollTo(0, srollHeight);
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }

    @Override
    void getVcode(String phone) {


        mModel.getVcode(new ResultCallback<VcodePOJO>() {
            @Override
            public void onResponse(VcodePOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    vCode = response.getCode();
                    //开始倒计时
                    mView.startTimeCount();
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }
        }, phone);
    }

    @Override
    void login(String phone, String vCode, String mInviteCode,String password) {
        if (CommonUtils.isNull(phone)) {
            CCToast.showShort(App.getContext(), "手机号不能为空");
            return;
        }

        if (CommonUtils.isNull(vCode)) {
            CCToast.showShort(App.getContext(), "验证码不能为空");
            return;
        }

        if (CommonUtils.isNull(password)) {
            CCToast.showShort(App.getContext(), "密码不能为空");
            return;
        }

        if (!vCode.equals(App.vCode)) {
            CCToast.showShort(App.getContext(), "验证码不正确");
            return;
        }

        mModel.login(new ResultCallback<UserInfoPOJO>() {
            @Override
            public void onResponse(UserInfoPOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    UserInfoUtils.saveUserInfo(response);
                    mView.loginSuccess();
                } else {
                    mView.cancelTimer();
                    CCToast.showShort(App.getContext(), response.getMessage());
                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                mView.cancelTimer();
            }
        }, phone, mInviteCode,password);


    }



    @Override
    void loginV2(String phone,  String password) {
        if (CommonUtils.isNull(phone)) {
            CCToast.showShort(App.getContext(), "手机号不能为空");
            return;
        }
        if (CommonUtils.isNull(password)) {
            CCToast.showShort(App.getContext(), "密码不能为空");
            return;
        }


        mModel.loginV2(new ResultCallback<UserInfoPOJO>() {
            @Override
            public void onResponse(UserInfoPOJO response) {
                super.onResponse(response);
                if (response.isResultSuccess()) {
                    UserInfoUtils.saveUserInfo(response);
                    mView.loginSuccess();
                } else{
                    CCToast.showShort(App.getContext(), response.getMessage());

                }
            }

            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }
        }, phone, password);
    }


}
