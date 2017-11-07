package live.cnaidai.com.ccyb.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.user.LoginActivity;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.util.ACache;
import live.cnaidai.com.ccyb.util.CommonUtils;

/**
 * Created by ChristLu on 2015/10/26.
 */
public class GuideActivity extends BaseActivity {
    @Bind(R.id.logo)
    ImageView mLogo;
    // 定义页面内容
    private ViewPager vpAd;
    private GuideViewPage myAdapter;
    private List<View> views;
    private Button btLogin;
    private LinearLayout llViewPage;

    // 引导图片资源
    private static final int[] pics = {R.mipmap.di1, R.mipmap.di2,
            R.mipmap.di3};

    // 底部点的图片
    private ImageView[] dots;

    // 记录当前选中的位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);
        ButterKnife.bind(this);

        String openStatus = ACache.get(this).getAsString("firstOpen");
        if (!CommonUtils.isNull(openStatus) && "1".equals(openStatus)) {
            CommonUtils.launchActivity(this, LoginV2Activity.class);
            finish();
        }

        views = new ArrayList<View>();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }

        llViewPage = (LinearLayout) findViewById(R.id.view_pager_content);
        // vpAd = (ViewPager)findViewById(R.id.vp_ad);
        // 初始化adapter
        vpAd = new ViewPager(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        vpAd.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels,
                dm.heightPixels));
        llViewPage.addView(vpAd);
        myAdapter = new GuideViewPage(views);
        vpAd.setAdapter(myAdapter);

        // 绑定回调
        vpAd.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            // 在这里可以添加跳转到主Activity的按钮
            @Override
            public void onPageSelected(int position) {
                // 页面选中的时候设置底部小圆点选中
                setCurDot(position);
                // 1.在布局中添加一个Button，设置属性:Visable为gone
                // 2.监控当前界面显示的位置，当图片为最后一张图片的时候，设置Visable为visable
                if (position == 2) {
                    btLogin.setVisibility(View.VISIBLE);
                    mLogo.setVisibility(View.VISIBLE);
                    btLogin.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            ACache.get(App.getContext()).put("firstOpen", "1");
                            Intent intent = new Intent(GuideActivity.this,
                                    LoginV2Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btLogin = (Button) findViewById(R.id.bt_login);
        initDot();
    }

    @Override
    public void initPresenter() {

    }

    private void initDot() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_dots);
        dots = new ImageView[pics.length];


        // 循环初始化小圆点图片
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// 设置所有小圆点未选中
            dots[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(v.getTag() + "");
                    setCurView(position);
                    setCurDot(position);
                }
            });
            dots[i].setTag(i);// 设置tag方便取出当前对应位置
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// 设置点为选中状态
    }

    // 设置当前的引导页
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }

        vpAd.setCurrentItem(position);
    }

    // 设置当前引导的圆点的选中
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1
                || currentIndex == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        btLogin.setVisibility(View.INVISIBLE);
        mLogo.setVisibility(View.INVISIBLE);
        currentIndex = position;
    }
}
