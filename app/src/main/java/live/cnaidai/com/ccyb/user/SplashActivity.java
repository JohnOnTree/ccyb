package live.cnaidai.com.ccyb.user;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseActivity;
import live.cnaidai.com.ccyb.base.GuideActivity;
import live.cnaidai.com.ccyb.service.LocationService;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CcSharedPreferences;


/**
 * @author lujs
 * @version V1.0
 * @Package com.aidai.activity
 * @Description: 启动页
 * @date 2015/9/11 14:41
 */
public class SplashActivity extends BaseActivity {
    private ImageView mIvSplash;
    private AnimationDrawable animationDrawable;

    private int i;

    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startService(new Intent(this, LocationService.class));


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }, 1000);

        initViews();
    }

    @Override
    public void initPresenter() {

    }


    private void initViews() {
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                login();


            }
        };

    }


    private void login() {
        //如果首次使用，true作为默认值,显示引导页
        boolean isFirstUser = Boolean.parseBoolean(CcSharedPreferences.get(mContext, CcConst.KEY_USE_FIRST, true).toString());
        if (isFirstUser) {
            //显示引导页
            CcSharedPreferences.put(mContext, CcConst.KEY_USE_FIRST, false);
            Intent intent = new Intent(mContext, GuideActivity.class);
            startActivity(intent);
            CcSharedPreferences.put(mContext, CcConst.KEY_USE_FIRST, false);

        } else {
            Intent intent = new Intent(mContext, LoginV2Activity.class);
            startActivity(intent);
        }
        finish();
    }
}
