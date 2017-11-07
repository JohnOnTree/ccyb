package live.cnaidai.com.ccyb.st;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseFragment;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGANormalRefreshViewHolder;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshLayout;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshViewHolder;

/**
 * Created by ChristLu on 16/11/14.
 */

public class StFragment extends BaseFragment<StPresenter, StModel> implements StContract.View {
    @Bind(R.id.recylerview)
    RecyclerView mRecylerview;
    @Bind(R.id.ref_layout)
    BGARefreshLayout mRefLayout;

    private StAdapter mStAdapter ;

    private ArrayList<String> urls = new ArrayList<>();

    private int pageSize = 1;

    private boolean isLoadMore;
    private boolean isRefresh;


    private List<StPOJO.DataEntity> mDataEntities = new ArrayList<>() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_st, null);
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
                mDataEntities.clear();
                pageSize = 1;
                isRefresh = true;
                isLoadMore = false;
                mPresenter.getStDatas(pageSize);

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                pageSize++;
                mPresenter.getStDatas(pageSize);
                isLoadMore = true;
                isRefresh = false;
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
        getStDatas();
    }

    private void getStDatas() {
        mPresenter.getStDatas(pageSize);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(StFragment.this, mModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void setStDatas(List<StPOJO.DataEntity> datas) {
        if (isRefresh || pageSize == 1) {
            mDataEntities.clear();
        }
        mDataEntities.addAll(datas);

        if (mStAdapter == null) {
            mStAdapter = new StAdapter(getActivity(),R.layout.item_st,datas);
            mRecylerview.setAdapter(mStAdapter);
        } else {
            mStAdapter.notifyDataSetChanged();
        }

        mRefLayout.endRefreshing();
        mRefLayout.endLoadingMore();

    }
}
