package live.cnaidai.com.ccyb.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionGen;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.util.TUtil;


/**
 * Created by ChristLu on 16/11/14.
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements BaseView {
    private static List<Activity> sActivities = new ArrayList<>();
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RelativeLayout mRlTitleLayout;
    ImageView mIvLeft;
    TextView mTvTitle;

    ImageView mIvRight ;

    TextView mTvRight ;

    private Handler mHandler;

    //分割线
    public View viewDivider ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);
        mRlTitleLayout = (RelativeLayout) findViewById(R.id.rl_title);
        mIvLeft = (ImageView) mRlTitleLayout.findViewById(R.id.iv_left);
        mIvRight = (ImageView) mRlTitleLayout.findViewById(R.id.iv_right);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        viewDivider =findViewById(R.id.view);


        initView();
        initPresenter();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionGen.needPermission(this, 100,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.CAMERA
                    }
            );
        }

    }

    private void initView() {
        sActivities.add(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initPresenter();
        //设置返回按钮默认显示
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                BaseActivity.finishAll();
            }
        };

    }

    /**
     * 设置内容标题
     *
     * @param title 用户设置的标题
     */
    public void setMainTitle(String title) {
        mTvTitle.setText(title);
    }

    /**
     * 设置主内容区域
     *
     * @param resId 主资源文件Id
     */
    public void setMainContentView(int resId) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resId, null);
        frameLayout.addView(v);
    }


    /**
     * 静态方法结束所有Activity
     */
    public static void finishAll() {
        for (Activity a : sActivities) {
            if (a instanceof Activity && !a.isFinishing()) {
                a.finish();
                a.overridePendingTransition(0, 0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        sActivities.remove(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 设置左边图标
     *
     * @param visible 是否显示
     */
    public void setLeftButtonVisible(int visible, View.OnClickListener listener) {
        mIvLeft.setVisibility(visible);
        mIvLeft.setOnClickListener(listener);

    }


    public void setRightTv(int visible,String rightStr, View.OnClickListener listener){
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(rightStr);
        mTvRight.setOnClickListener(listener);
    }



    public void setRightIv(int resId, View.OnClickListener listener){
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setImageResource(resId);
        mIvRight.setOnClickListener(listener);
    }


    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();


}
