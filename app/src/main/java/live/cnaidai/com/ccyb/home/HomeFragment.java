package live.cnaidai.com.ccyb.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseFragment;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.home.V3.HomeDataV3POJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.util.ACache;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.GsonUtil;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.view.ImageCycleView;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGANormalRefreshViewHolder;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshLayout;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshViewHolder;

/**
 * Created by ChristLu on 16/11/14.
 */

public class HomeFragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View {

    @Bind(R.id.icv_home)
    ImageCycleView mIcvHome;
    @Bind(R.id.recylerview)
    RecyclerView mRecylerview;
    @Bind(R.id.ref_layout)
    BGARefreshLayout mRefLayout;

    private ArrayList<String> urls = new ArrayList<>();

    private int pageSize = 1;

    private List<HomeDataPOJO.DataEntity.IndexAdvertListEntity> mAdDatas;

    private List<HomeDataPOJO.DataEntity.IndexGiftListEntity> mGifts = new ArrayList<>();

    private GiftAdapter mGiftAdapter;

    private boolean isLoadMore ;
    private  boolean isRefresh ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initRef();
        return view;
    }

    private void initRef() {
        mRecylerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // 为BGARefreshLayout设置代理
        mRefLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                mGifts.clear();
                pageSize = 1;
                isRefresh = true;
                isLoadMore = false ;
                mPresenter.getHomeAd(pageSize+"");            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                pageSize++;
                mPresenter.getHomeAd(pageSize+"");
                isLoadMore = true ;
                isRefresh = false ;
                return true;
            }
        });
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefLayout.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String jsonInfo = ACache.get(getActivity()).getAsString("homedata");
        if (!CommonUtils.isNull(jsonInfo)) {
            HomeDataPOJO response = GsonUtil.convertJsonToObj(jsonInfo, HomeDataPOJO.class);
            if (response != null) {
                setAdDatas(response.getData().getIndexAdvertList());
                setGifts(response.getData().getIndexGiftList());
            }
        }


        getHomeAd();
    }

    private void getHomeAd() {
        mPresenter.getHomeAd(pageSize+"");
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(HomeFragment.this, mModel);
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
        if (!isAdded()) {
            return;
        }
        urls.clear();
        mAdDatas = adDatas;
        for (HomeDataPOJO.DataEntity.IndexAdvertListEntity advertListEntity : adDatas) {
            urls.add(BuildConfig.SERVER_PATH + advertListEntity.getAdPic());
        }
        mIcvHome.setImageResources(urls, mImageCycleViewListener);
    }


    ImageCycleView.ImageCycleViewListener mImageCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, SimpleDraweeView imageView) {
            ImageUtils.loadImage(imageView, imageURL);
        }

        @Override
        public void onImageClick(int position, View imageView) {
            Bundle bundle = new Bundle();
            bundle.putString("url", BuildConfig.SERVER_PATH+mAdDatas.get(position).getAdHtml5());
            bundle.putString("title", mAdDatas.get(position).getName());
            CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
        }
    };

    /**
     * 设置礼品清单
     *
     * @param giftDatas
     */
    @Override
    public void setGifts(List<HomeDataPOJO.DataEntity.IndexGiftListEntity> giftDatas) {
        if (!isAdded()) {
            return;
        }
        if(isRefresh||pageSize==1){
            mGifts.clear();
        }
        mGifts.addAll(giftDatas);
        if(mGiftAdapter == null) {
            mGiftAdapter = new GiftAdapter(getActivity(), R.layout.item_gift_home, mGifts);
            mRecylerview.setAdapter(mGiftAdapter);
        }else{
            mGiftAdapter.notifyDataSetChanged();
        }

        mRefLayout.endRefreshing();
        mRefLayout.endLoadingMore();

    }

    @Override
    public void setAds(List<UsersPOJO.DataEntity.IndexAdvertListEntity> ads) {

    }

    @Override
    public void setUsers(List<UsersPOJO.DataEntity.MemberListEntity> users) {

    }

    @Override
    public void setDatas(HomeDataV3POJO v3POJO) {

    }
}
