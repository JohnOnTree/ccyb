package live.cnaidai.com.ccyb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.base.BaseActivity;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.giftmarket.Shop_v2Fragment;
import live.cnaidai.com.ccyb.home.V3.HomeV3Fragment;
import live.cnaidai.com.ccyb.member.MemberFragment;
import live.cnaidai.com.ccyb.st.StFragment;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.view.ArcMenu;

public class MainV2Activity extends BaseActivity {
    @Bind(R.id.RadioG_Bottem)
    RadioGroup mRgTab;
    @Bind(R.id.arcMenu)
    ArcMenu mArcMenu;

    private FragmentManager mFragmentManager;
    private int preResId;
    private boolean fabOpend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        ButterKnife.bind(this);
        initContentView();

    }

    private void initContentView() {
        mFragmentManager = getSupportFragmentManager();
        int index = getIntent().getIntExtra("index", 0);
        int resId = R.id.tab_home;
        switch (index) {
            case 0:
                resId = R.id.tab_home;
                break;
            case 1:
                resId = R.id.tab_shop;
                break;
            default:
                break;
        }
        preResId = resId;


        setTabChecked(resId);
        mRgTab.setOnCheckedChangeListener(mOnCheckChanaged);

        mArcMenu.setStatusChange(new ArcMenu.StatusChange() {
            @Override
            public void arcMenuStatus(ArcMenu.Status mStatus) {
                mArcMenu.setBackgroundColor(
                        mStatus == ArcMenu.Status.OPEN ? getResources().getColor(R.color.color_half_gray) : Color.TRANSPARENT);
            }
        });

        mArcMenu.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                String title = "";
                String url = "";
                Bundle bundle = new Bundle();

                switch (pos) {
                    case 0:
                        //被约观影
                        bundle.putBoolean("closeWeb",true);
                        title = "想被人约";
                        url = BuildConfig.SERVER_PATH + "invitation_beinvitedView.do?appType=3&memberId=" + UserInfoUtils.getMemberId();
                        break;
                    case 1:
                        //约人观影
                        title = "约人观影";
                        bundle.putBoolean("closeWeb",true);
                        url = BuildConfig.SERVER_PATH + "invitation_invitationView.do?appType=3&memberId=" + UserInfoUtils.getMemberId();
                        break;
                    case 2:
                        //约人喝酒
                        title = "酒吧约会";
                        bundle.putBoolean("closeWeb",true);
                        url = BuildConfig.SERVER_PATH + "invitation_invitationBar.do?appType=3&memberId=" + UserInfoUtils.getMemberId();
                        break;
                    case 3:
                        //被约喝酒
                        title = "想被人约";
                        bundle.putBoolean("closeWeb",true);
                        url = BuildConfig.SERVER_PATH + "invitation_beinvitedBar.do?appType=3&memberId=" + UserInfoUtils.getMemberId();
                        break;

                }
                bundle.putString("url", url);
                bundle.putString("title", title);
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);

            }
        });

    }


    RadioGroup.OnCheckedChangeListener mOnCheckChanaged = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != R.id.tab_home_bg) {
                setTabChecked(checkedId);
                preResId = checkedId;
                if (checkedId != R.id.tab_home) {
//                mMenuMore.setVisibility(View.GONE);
                    if (checkedId == R.id.tab_mine) {
                        if (!UserInfoUtils.isLogin()) {
                            CommonUtils.launchActivity(mContext, LoginV2Activity.class);
                        }
                    }
                }

            } else {
//                mMenuMore.setVisibility(View.VISIBLE);
            }
        }
    };

    private void setTabChecked(int checkedId) {
        ((RadioButton) findViewById(checkedId)).setChecked(true);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = FragmentFactory.getInstanceByIndex(checkedId);
        transaction.replace(R.id.FrameAct_FragmentGroup, fragment);
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



    /**
     * 工厂方法，根据id获得不同的fragment
     *
     * @author lujs
     */
    public static class FragmentFactory {
        public static Fragment getInstanceByIndex(int id) {
            Fragment fragment = null;
            switch (id) {
                case R.id.tab_home:
                    fragment = new HomeV3Fragment();
                    break;
                case R.id.tab_shop:
                    fragment = new Shop_v2Fragment();
                    break;
                case R.id.tab_st:
                    fragment = new StFragment();
                    break;
                case R.id.tab_mine:
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
    }

}
