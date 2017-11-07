package live.cnaidai.com.ccyb.giftmarket;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseFragmentAdapter;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;

/**
 * description:
 * autour: ChristLu
 * date: 17/7/23
 * package: live.cnaidai.com.ccyb.giftmarket
 */
public class Shop_v2Fragment extends Fragment {
    @Bind(R.id.tab)
    SlidingTabLayout mTab;
    @Bind(R.id.vp_FindFragment_pager)
    ViewPager mVpFindFragmentPager;
    @Bind(R.id.iv_search)
    ImageView mIvSearch;

    //fragment适配器
    private BaseFragmentAdapter mFragmentAdapter;
    //Frament列表
    private List<Fragment> mFragmentList = new ArrayList<>();

    private String[] mTitles = {
            "酒吧", "电影"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_v2, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
    }


    private void initFragment() {
        ShopFragment shopFragment = new ShopFragment();
        BarFragment barFragment = new BarFragment();
        mFragmentList.add(barFragment);
        mFragmentList.add(shopFragment);

        mFragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitles);
        mVpFindFragmentPager.setAdapter(mFragmentAdapter);
        mVpFindFragmentPager.setOffscreenPageLimit(3);
        mTab.setViewPager(mVpFindFragmentPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.iv_search)
    public void onClick() {
        //搜索
        Bundle bundle = new Bundle();
        bundle.putString("url", CcConst.URL_GOOD_SEARCH);
        bundle.putString("title", "礼品搜索");
        CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
    }
}
