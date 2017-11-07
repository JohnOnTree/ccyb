package live.cnaidai.com.ccyb.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.MainV2Activity;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseActivity;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;

/**
 * Created by ChristLu on 16/11/18.
 */

public class LoginV2Activity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @Bind(R.id.met_mobile)
    MaterialEditText mMetMobile;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.root)
    RelativeLayout mRoot;
    @Bind(R.id.et_password)
    MaterialEditText mEtPwd;

    private String phone;

    private String pwd;

    private Bundle mBundle;

    private boolean isPay;

    private String payUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v2);
        ButterKnife.bind(this);

        if (UserInfoUtils.isLogin()) {
            CommonUtils.launchActivity(this, MainV2Activity.class);
            finish();
        }

        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            isPay = mBundle.getBoolean("isPay");
            payUrl = mBundle.getString("url");
        }

//        mPresenter.setSoftKeyBoard(mRoot, mBtnLogin);


    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.btn_login, R.id.iv_close,R.id.tv_register,R.id.tv_getback_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //登录
                login();
                break;
            case R.id.iv_close:
                CommonUtils.launchActivity(this, MainV2Activity.class);
                finish();
                break;
            case R.id.tv_register:
                CommonUtils.launchActivity(this, LoginActivity.class);
                break;
            case R.id.tv_getback_pwd:
                //找回密码
                CommonWebActivity.openWebActivity(mContext,"忘记密码", BuildConfig.SERVER_PATH+"mobileMember_showForgetPassword.do?appType=3", mContext.getResources().getColor(R.color.color_bg_half_black));
                break;
        }
    }

    private void login() {
        pwd = mEtPwd.getText().toString();
        phone = mMetMobile.getText().toString();
        mPresenter.loginV2(phone, pwd);
    }

    @Override
    public void startTimeCount() {
    }

    @Override
    public void loginSuccess() {
        //登录成功
        if (isPay) {
            mBundle.putString("url", payUrl + "&memberId=" + UserInfoUtils.getMemberId());
            mBundle.putString("title", "付款确认");
            CommonUtils.launchActivity(mContext, CommonWebActivity.class, mBundle);

        } else {
            CommonUtils.launchActivity(this, MainV2Activity.class);
        }
        finish();
    }

    @Override
    public void cancelTimer() {

    }


}
