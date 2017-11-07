package live.cnaidai.com.ccyb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import live.cnaidai.com.ccyb.base.BaseActivity;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.giftmarket.GiftMarketFragment;
import live.cnaidai.com.ccyb.home.HomeV2Fragment;
import live.cnaidai.com.ccyb.member.MemberFragment;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;

public class MainActivity extends BaseActivity {
    @Bind(R.id.rg_tab)
    RadioGroup mRgTab;
    @Bind(R.id.tv_cloud)
    TextView mTvCloud;
    @Bind(R.id.item_yrgy)
    FloatingActionButton mItemYrgy;
    @Bind(R.id.item_xbry)
    FloatingActionButton mItemXbry;
    @Bind(R.id.menu_more)
    FloatingActionMenu mMenuMore;

    private FragmentManager mFragmentManager;
    private int preResId;
    private boolean fabOpend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
		initContentView();



        setFab();


    }

    private void setFab() {
        mItemYrgy.setLabelColors(android.R.color.transparent, android.R.color.transparent, android.R.color.transparent);
        mItemYrgy.setLabelTextColor(R.color.color_black);
        mItemXbry.setLabelColors(android.R.color.transparent, android.R.color.transparent, android.R.color.transparent);
        mItemXbry.setLabelTextColor(R.color.color_black);
        mMenuMore.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if(opened){
                    openMenu();
                }else{
                    closeMenu();
                }
            }
        });
    }

    private void initContentView() {
        mFragmentManager = getSupportFragmentManager();
        int resId = R.id.rb_home;
        preResId = resId;
        setTabChecked(resId);
        mRgTab.setOnCheckedChangeListener(mOnCheckChanaged);
    }


    RadioGroup.OnCheckedChangeListener mOnCheckChanaged = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTabChecked(checkedId);
            preResId = checkedId;
            if (checkedId != R.id.rb_home) {
                mMenuMore.setVisibility(View.GONE);
                if(checkedId == R.id.rb_mine){
                    if(!UserInfoUtils.isLogin()){
                        CommonUtils.launchActivity(mContext, LoginV2Activity.class);
                    }
                }
            } else {

                mMenuMore.setVisibility(View.VISIBLE);
            }
        }
    };

    private void setTabChecked(int checkedId) {
        ((RadioButton) findViewById(checkedId)).setChecked(true);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = FragmentFactory.getInstanceByIndex(checkedId);
        transaction.replace(R.id.fl_contnet, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((RadioButton) findViewById(preResId)).setChecked(true);
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.item_yrgy, R.id.item_xbry, R.id.tv_cloud})
    public void onClick(View view) {
        if(!UserInfoUtils.isLogin()){
            CommonUtils.launchActivity(mContext, LoginV2Activity.class);
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean("isHomePage",true);

        switch (view.getId()) {
            case R.id.item_yrgy:
                //想被人约
                bundle.putString("url", BuildConfig.SERVER_PATH+"invitation_beinvitedView.do?appType=3&memberId="+UserInfoUtils.getMemberId());
                bundle.putString("title", "被约票");
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);


                break;
            case R.id.item_xbry:
                //约人观影
                bundle.putString("url", BuildConfig.SERVER_PATH+"invitation_invitationView.do?appType=3&memberId="+UserInfoUtils.getMemberId());
                bundle.putString("title", "约票");
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
                break;
            case R.id.tv_cloud:
                if (fabOpend) {
                    closeMenu();
                }
                break;
        }
    }





    /**
     * 工厂方法，根据id获得不同的fragment
     *
     * @author lujs
     */
    public static class FragmentFactory {
        public static Fragment getInstanceByIndex(int id) {
            Fragment fragment = null;
            switch (id) {
                case R.id.rb_home:
                    fragment = new HomeV2Fragment();
                    break;
                case R.id.rb_market:
                    fragment = new GiftMarketFragment();
                    break;
                case R.id.rb_mine:
                    fragment = new MemberFragment();
                    break;
            }
            return fragment;
        }
    }

    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        super.onPause();
        closeMenu();
    }

    private void openMenu() {
        mTvCloud.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.9f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        mTvCloud.startAnimation(alphaAnimation);
        fabOpend = true;

    }

    private void closeMenu() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.9f, 0);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        mTvCloud.startAnimation(alphaAnimation);
        mTvCloud.clearAnimation();
        mTvCloud.setVisibility(View.GONE);
        fabOpend = false;
        mMenuMore.close(true);
    }
}
