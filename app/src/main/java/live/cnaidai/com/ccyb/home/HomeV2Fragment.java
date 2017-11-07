package live.cnaidai.com.ccyb.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseFragment;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.home.V3.HomeDataV3POJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.view.ImageCycleView;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGANormalRefreshViewHolder;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshLayout;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshViewHolder;

/**
 * Created by ChristLu on 16/11/14.
 */

public class HomeV2Fragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View {

    @Bind(R.id.icv_home)
    ImageCycleView mIcvHome;
    @Bind(R.id.recylerview)
    RecyclerView mRecylerview;
    @Bind(R.id.ref_layout)
    BGARefreshLayout mRefLayout;
    @Bind(R.id.tv_city)
    TextView mTvCity;

    private ArrayList<String> urls = new ArrayList<>();

    private int pageSize = 1;

    private List<UsersPOJO.DataEntity.IndexAdvertListEntity> mAds;
    private List<UsersPOJO.DataEntity.MemberListEntity> mUsers = new ArrayList<>();

    private UserAdapter mUserAdapter;

    private boolean isLoadMore;
    private boolean isRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initRef();
        return view;
    }

    private void initRef() {
        mRecylerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 为BGARefreshLayout设置代理
        mRefLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//                mUsers.clear();
//                pageSize = 1;
//                isRefresh = true;
//                isLoadMore = false;
//                mPresenter.getUsers(pageSize + "", UserInfoUtils.getMemberId());
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                pageSize++;
                mPresenter.getUsers(pageSize + "", UserInfoUtils.getMemberId());
                isLoadMore = true;
                isRefresh = false;
                return true;
            }
        });
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefLayout.setRefreshViewHolder(refreshViewHolder);
        mRefLayout.setPullDownRefreshEnable(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        String jsonInfo = ACache.get(getActivity()).getAsString("homedata");
//        if (!CommonUtils.isNull(jsonInfo)) {
//            HomeDataV3POJO response = GsonUtil.convertJsonToObj(jsonInfo, HomeDataV3POJO.class);
//            if (response != null) {
//                setAds(response.getData().geT);
//                setUsers(response.getData().getIndexGiftList());
//            }
//        }

        mTvCity.setText(App.sCity);
        getHomeAd();
    }

    private void getHomeAd() {
        mPresenter.getUsers(pageSize + "", UserInfoUtils.getMemberId());
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(HomeV2Fragment.this, mModel);
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


    ImageCycleView.ImageCycleViewListener mImageCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, SimpleDraweeView imageView) {
            ImageUtils.loadImage(imageView, imageURL);
        }

        @Override
        public void onImageClick(int position, View imageView) {
            Bundle bundle = new Bundle();
            bundle.putString("url", BuildConfig.SERVER_PATH + mAds.get(position).getAdHtml5());
            bundle.putString("title", mAds.get(position).getName());
            bundle.putInt("objId",mAds.get(position).getObjId());
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


    }

    @Override
    public void setAds(List<UsersPOJO.DataEntity.IndexAdvertListEntity> ads) {
        if (!isAdded()) {
            return;
        }
        urls.clear();
        mAds = ads;
        for (UsersPOJO.DataEntity.IndexAdvertListEntity advertListEntity : mAds) {
            urls.add(BuildConfig.SERVER_PATH + advertListEntity.getAdPic());
        }
        mIcvHome.setImageResources(urls, mImageCycleViewListener);
    }

    @Override
    public void setUsers(List<UsersPOJO.DataEntity.MemberListEntity> users) {
        if (!isAdded()) {
            return;
        }
        if (isRefresh || pageSize == 1) {
            mUsers.clear();
        }
        mUsers.addAll(users);
        if (mUserAdapter == null) {
            mUserAdapter = new UserAdapter(getActivity(), R.layout.item_user, mUsers);
            mRecylerview.setAdapter(mUserAdapter);
        } else {
            mUserAdapter.notifyDataSetChanged();
        }

        mRefLayout.endRefreshing();
        mRefLayout.endLoadingMore();
    }

    @Override
    public void setDatas(HomeDataV3POJO v3POJO) {

    }
}
