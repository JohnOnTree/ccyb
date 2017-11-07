package live.cnaidai.com.ccyb.home.V3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseFragment;
import live.cnaidai.com.ccyb.base.BaseFragmentAdapter;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.home.HomeContract;
import live.cnaidai.com.ccyb.home.HomeModel;
import live.cnaidai.com.ccyb.home.HomePresenter;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.view.ImageCycleView;

import static live.cnaidai.com.ccyb.R.id.tab;

/**
 * Created by ChristLu on 16/11/14.
 */

public class HomeV3Fragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View {


    @Bind(R.id.rv_hot)
    RecyclerView mRvHot;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(tab)
    SlidingTabLayout mTab;
    @Bind(R.id.vp_FindFragment_pager)
    ViewPager mVpFindFragmentPager;
    @Bind(R.id.icv)
    ImageCycleView mIcv;
    private boolean isLoadMore;
    private boolean isRefresh;

    private List<HomeDataV3POJO.DataEntity.MemberListEntity> mHotDatas;


    //子fragment的标题
    private String[] mTitles = {
            "最新", "附近的人"
    };
    //fragment适配器
    private BaseFragmentAdapter mFragmentAdapter;
    //Frament列表
    private List<Fragment> mFragmentList = new ArrayList<>();

    private ArrayList<String> urls = new ArrayList<>();


    private List<HomeDataV3POJO.DataEntity.AdvertInfo> mAdDatas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_v3, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvCity.setText(App.sCity);
        mPresenter.getHomeDatas("1");
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(HomeV3Fragment.this, mModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 设置广告轮播图
     *
     * @param adDatas
     */
    @Override
    public void setAdDatas(List<HomeDataPOJO.DataEntity.IndexAdvertListEntity> adDatas) {
    }


    /**
     * 设置礼品清单
     *
     * @param giftDatas
     */
    @Override
    public void setGifts(List<HomeDataPOJO.DataEntity.IndexGiftListEntity> giftDatas) {


    }

    @Override
    public void setAds(List<UsersPOJO.DataEntity.IndexAdvertListEntity> ads) {
        if (!isAdded()) {
            return;
        }

    }

    @Override
    public void setUsers(List<UsersPOJO.DataEntity.MemberListEntity> users) {
        if (!isAdded()) {
            return;
        }
    }

    @Override
    public void setDatas(HomeDataV3POJO v3POJO) {
        if (!isAdded()) {
            return;
        }
        //设置热门推荐
        mHotDatas = v3POJO.getData().getHotMemberList();
        setupHotDatas(mHotDatas);
        //设置选项卡
        setSelectCard(v3POJO);

        //设置轮播图
        mAdDatas = v3POJO.getData().getIndexAdvertList();
        if (mAdDatas != null && mAdDatas.size() > 0) {
            for (HomeDataV3POJO.DataEntity.AdvertInfo advertInfo : mAdDatas) {
                urls.add(BuildConfig.SERVER_PATH + advertInfo.getAdPic());
            }
        }
        mIcv.setImageResources(urls, mImageCycleViewListener);


    }


    ImageCycleView.ImageCycleViewListener mImageCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, SimpleDraweeView imageView) {
            ImageUtils.loadImage(imageView, imageURL);
        }

        @Override
        public void onImageClick(int position, View imageView) {
            Bundle bundle = new Bundle();
            bundle.putString("url", BuildConfig.SERVER_PATH + mAdDatas.get(position).getAdHtml5());
            bundle.putString("title", mAdDatas.get(position).getName());
            CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
        }
    };


    private void setSelectCard(HomeDataV3POJO v3POJO) {
        initFragment(v3POJO);
        mFragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitles);
        mVpFindFragmentPager.setAdapter(mFragmentAdapter);
        mVpFindFragmentPager.setOffscreenPageLimit(3);
        mTab.setViewPager(mVpFindFragmentPager);
    }

    private void initFragment(HomeDataV3POJO v3POJO) {
        NewsFragment newMemberFragment = new NewsFragment();
        newMemberFragment.setNewsDatas(v3POJO.getData().getNewMemberList());
        newMemberFragment.setType(0);
        NearbyFragment neaebyFragment = new NearbyFragment();
        neaebyFragment.setNearbyDatas(v3POJO.getData().getNearMemberList());
        neaebyFragment.setType(1);
        mFragmentList.add(newMemberFragment);
        mFragmentList.add(neaebyFragment);
    }

    private void setupHotDatas(List<HomeDataV3POJO.DataEntity.MemberListEntity> hotDatas) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvHot.setLayoutManager(linearLayoutManager);
        mRvHot.setAdapter(new HotDataAdapter(getActivity(), R.layout.item_hot, hotDatas));
    }


}
