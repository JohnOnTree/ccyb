package live.cnaidai.com.ccyb.giftmarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseFragment;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.giftmarket.pojo.GiftsPOJO;
import live.cnaidai.com.ccyb.home.GiftAdapter;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.util.ACache;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.GsonUtil;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGANormalRefreshViewHolder;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshLayout;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshViewHolder;

/**
 * Created by ChristLu on 16/11/14.
 */

public class GiftMarketFragment extends BaseFragment<GiftMarketPresenter, GiftMarketModel> implements GiftMarketContract.View {
    @Bind(R.id.recylerview)
    RecyclerView mRecylerview;
    @Bind(R.id.ref_layout)
    BGARefreshLayout mRefLayout;

    private ArrayList<String> urls = new ArrayList<>();

    private int pageSize = 1;


    private GiftAdapter mGiftAdapter;

    private boolean isLoadMore ;
    private  boolean isRefresh ;

    private List<HomeDataPOJO.DataEntity.IndexGiftListEntity> mGifts = new ArrayList<>() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift_market, null);
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
                mPresenter.getGifts(pageSize+"");           }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                pageSize++;
                mPresenter.getGifts(pageSize+"");
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
        //首先加载本地的
        String gifts = ACache.get(App.getContext()).getAsString("gifts");
        if (!CommonUtils.isNull(gifts)) {
            GiftsPOJO giftsPOJO = GsonUtil.convertJsonToObj(gifts, GiftsPOJO.class);
            if (giftsPOJO != null) {
                setGifts(giftsPOJO.getData().getTicketList());
            }
        }

        getGifts();
    }

    private void getGifts() {
        mPresenter.getGifts(pageSize+"");
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(GiftMarketFragment.this, mModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

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
            mGiftAdapter = new GiftAdapter(getActivity(), R.layout.item_gift_home_v2, mGifts);
            mRecylerview.setAdapter(mGiftAdapter);
        }else{
            mGiftAdapter.notifyDataSetChanged();
        }

        mRefLayout.endRefreshing();
        mRefLayout.endLoadingMore();


    }

    @Override
    public void setBars(List<GiftsPOJO.DataEntity.BarListEntity> giftDatas) {

    }

    @OnClick(R.id.iv_right)
    public void onClick() {
        //搜索
        Bundle bundle = new Bundle();
        bundle.putString("url", CcConst.URL_GOOD_SEARCH);
        bundle.putString("title", "礼品搜索");
        CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
    }
}
