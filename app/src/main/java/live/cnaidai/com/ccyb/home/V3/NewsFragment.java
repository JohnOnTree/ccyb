package live.cnaidai.com.ccyb.home.V3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.okhttp.Request;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.api.HomeAPI;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.view.SelectPicPopupWindow;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGANormalRefreshViewHolder;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshLayout;
import live.cnaidai.com.ccyb.weight.refreshlayout.BGARefreshViewHolder;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/17
 * package: live.cnaidai.com.ccyb.home.V3
 */
public class NewsFragment extends Fragment {


    @Bind(R.id.rcv_news)
    RecyclerView mRcvNews;
    @Bind(R.id.ref_layout)
    BGARefreshLayout mRefLayout;
    @Bind(R.id.root)
    LinearLayout mRoot;
    private List<HomeDataV3POJO.DataEntity.MemberListEntity> mNewsDatas;

    private NewsAdapter mNewsAdapter;

    private int pageIndex = 1;

    private boolean isLoadMore;
    private boolean isRefresh;

    private SelectPicPopupWindow mSelectPicPopupWindow;

    private int type ;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<HomeDataV3POJO.DataEntity.MemberListEntity> getNewsDatas() {
        return mNewsDatas;
    }



    public void setNewsDatas(List<HomeDataV3POJO.DataEntity.MemberListEntity> newsDatas) {
        mNewsDatas = newsDatas;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRcvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsAdapter = new NewsAdapter(getActivity(), R.layout.item_user, mNewsDatas, mYueListener,type);
        mRcvNews.setAdapter(mNewsAdapter);

        initRef();

    }

    YueRenListener mYueListener = new YueRenListener() {
        @Override
        public void showWindows(final HomeDataV3POJO.DataEntity.MemberListEntity user) {
            mSelectPicPopupWindow = new SelectPicPopupWindow(getContext(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    switch (v.getId()) {
                        case R.id.ll_qinghejiu:
                            //TODO 请喝酒
                            // 请喝酒
                            bundle.putString("url", BuildConfig.SERVER_PATH + "invitation_invitationBar.do?appType=3&choiceMemberId=" + user.getMemberId() + "&memberId=" + UserInfoUtils.getMemberId());
                            bundle.putString("title", "酒吧约会");
                            CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
                            break;
                        case R.id.ll_qingkandianying:
                            //请观影
                            bundle.putString("url", BuildConfig.SERVER_PATH + "invitation_invitationView.do?appType=3&choiceMemberId=" + user.getMemberId() + "&memberId=" + UserInfoUtils.getMemberId());
                            bundle.putString("title", "约人观影");
                            CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
                            break;
                    }
                    mSelectPicPopupWindow.dismiss();
                }
            });
            mSelectPicPopupWindow.showAtLocation(mRoot, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    };


    private void initRef() {
        mRcvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRefLayout.setPullDownRefreshEnable(false);

        // 为BGARefreshLayout设置代理
        mRefLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                mNewsDatas.clear();
                pageIndex = 1;
                isRefresh = true;
                isLoadMore = false;
                HomeAPI.getInstance().getHomeDatas(callback, pageIndex + "");
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                pageIndex++;
                HomeAPI.getInstance().getHomeDatas(callback, pageIndex + "");
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

    ResultCallback<HomeDataV3POJO> callback = new ResultCallback<HomeDataV3POJO>() {
        @Override
        public void onResponse(HomeDataV3POJO response) {
            super.onResponse(response);
            if (response.isResultSuccess()) {
                if (isRefresh || pageIndex == 1) {
                    mNewsDatas.clear();
                }
                mNewsDatas.addAll(response.getData().getNewMemberList());

                if (mNewsAdapter == null) {
                    mNewsAdapter = new NewsAdapter(getActivity(), R.layout.item_gift_home_v2, mNewsDatas, mYueListener,type);
                    mRcvNews.setAdapter(mNewsAdapter);
                } else {
                    mNewsAdapter.notifyDataSetChanged();
                }

                mRefLayout.endRefreshing();
                mRefLayout.endLoadingMore();
            }
        }

        @Override
        public void onError(Request request, Exception e) {
            super.onError(request, e);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
