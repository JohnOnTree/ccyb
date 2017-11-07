package live.cnaidai.com.ccyb.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseActivity;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.util.CCToast;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;

/**
 * Created by ChristLu on 16/11/18.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @Bind(R.id.met_mobile)
    MaterialEditText mMetMobile;
    @Bind(R.id.tv_vcode_get)
    TextView mTvVcodeGet;
    @Bind(R.id.met_vcode)
    MaterialEditText mMetVcode;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.root)
    RelativeLayout mRoot;
    @Bind(R.id.et_inviteCode)
    MaterialEditText mEtInviteCode;
    @Bind(R.id.et_password)
    MaterialEditText mEtPassword;

    private TimeCount mTimeCount;

    private String phone;
    private String vCode;


    private String mInviteCode = "";
    private String mPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initArgs();


    }

    private void initArgs() {
//        mPresenter.setSoftKeyBoard(mRoot, mBtnLogin);
        mTimeCount = new TimeCount(60 * 1000, 1000);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.tv_vcode_get, R.id.btn_login, R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_vcode_get:
                //获得验证码
                getVcode();
                break;
            case R.id.btn_login:
                //注册
                login();
                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }

    private void getVcode() {
        phone = mMetMobile.getText().toString();
        if (CommonUtils.isNull(phone)) {
            CCToast.showShort(App.getContext(), "手机号不能为空");
            return;
        }
        mTimeCount.start();
        mPresenter.getVcode(phone);
    }

    private void login() {
        vCode = mMetVcode.getText().toString();
        mInviteCode = mEtInviteCode.getText().toString();
        mPassword = mEtPassword.getText().toString();
        mPresenter.login(phone, vCode, mInviteCode, mPassword);
    }

    @Override
    public void startTimeCount() {
        //开始倒计时
        mTimeCount.start();
    }

    @Override
    public void loginSuccess() {
        //登录成功
        Bundle mBundle = new Bundle();
        mBundle.putString("url", BuildConfig.SERVER_PATH+"mobileMember_showRegisterImprove.do?memberId=" + UserInfoUtils.getMemberId());
        mBundle.putString("title", "完善注册");
        CommonUtils.launchActivity(mContext, CommonWebActivity.class, mBundle);
        finish();
    }

    @Override
    public void cancelTimer() {
        mTimeCount.onFinish();
        mTimeCount.cancel();
    }


    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            mTvVcodeGet.setClickable(true);
            mTvVcodeGet.setText("获取验证码");
        }

        public void onTick(long millisUntilFinished) {
            mTvVcodeGet.setClickable(false);
            mTvVcodeGet.setText("(" + millisUntilFinished / 1000 + ")重新获取");
        }
    }
}
