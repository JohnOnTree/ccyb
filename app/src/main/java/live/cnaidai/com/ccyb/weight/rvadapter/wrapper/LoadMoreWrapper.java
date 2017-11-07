package live.cnaidai.com.ccyb.weight.rvadapter.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;
import live.cnaidai.com.ccyb.weight.rvadapter.utils.WrapperUtils;


/**
 * Created by zhy on 16/6/23.
 */
public class LoadMoreWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

	private RecyclerView.Adapter mInnerAdapter;
	private View mLoadMoreView;
	private int mLoadMoreLayoutId;
	private boolean mIsLoadMore = false;
	private RecyclerView mRecyclerView;

	public LoadMoreWrapper(RecyclerView.Adapter adapter, RecyclerView recycle) {
		mInnerAdapter = adapter;
		mRecyclerView = recycle;
		initLoadMore();
	}

	private void initLoadMore() {
		mRecyclerView.addOnScrollListener(new OnRcyScrollListener() {
			@Override
			public void onBottom() {
				//滑动到底部，显示加载更多
				mIsLoadMore = true;
				notifyDataSetChanged();
			}
		});
	}

	private boolean hasLoadMore() {
		return mLoadMoreView != null || mLoadMoreLayoutId != 0;
	}


	private boolean isShowLoadMore(int position) {
		return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
	}

	@Override
	public int getItemViewType(int position) {
		if (isShowLoadMore(position)) {
			return ITEM_TYPE_LOAD_MORE;
		}
		return mInnerAdapter.getItemViewType(position);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == ITEM_TYPE_LOAD_MORE) {
			ViewHolder holder;
			if (mLoadMoreView != null) {
				holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
			} else {
				holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
			}
			return holder;
		}
		return mInnerAdapter.onCreateViewHolder(parent, viewType);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (isShowLoadMore(position)) {
			if (mOnLoadMoreListener != null) {
				mOnLoadMoreListener.onLoadMoreRequested();
			}
			return;
		}
		mInnerAdapter.onBindViewHolder(holder, position);
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() {
			@Override
			public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
				if (isShowLoadMore(position)) {
					return layoutManager.getSpanCount();
				}
				if (oldLookup != null) {
					return oldLookup.getSpanSize(position);
				}
				return 1;
			}
		});
	}


	@Override
	public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
		mInnerAdapter.onViewAttachedToWindow(holder);

		if (isShowLoadMore(holder.getLayoutPosition())) {
			setFullSpan(holder);
		}
	}

	private void setFullSpan(RecyclerView.ViewHolder holder) {
		ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

		if (lp != null
			&& lp instanceof StaggeredGridLayoutManager.LayoutParams) {
			StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

			p.setFullSpan(true);
		}
	}

	@Override
	public int getItemCount() {
		int count = mInnerAdapter.getItemCount();
		if(hasLoadMore() && mIsLoadMore){
			count = count + 1;
		}

		return count;
	}


	public interface OnLoadMoreListener {
		void onLoadMoreRequested();
	}

	private OnLoadMoreListener mOnLoadMoreListener;

	public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
		if (loadMoreListener != null) {
			mOnLoadMoreListener = loadMoreListener;
		}
		return this;
	}

	public LoadMoreWrapper setLoadMoreView(View loadMoreView) {
		mLoadMoreView = loadMoreView;
		return this;
	}

	public LoadMoreWrapper setLoadMoreView(int layoutId) {
		mLoadMoreLayoutId = layoutId;
		return this;
	}

	public void LoadMoreState(boolean isLoadMore){
		mIsLoadMore = isLoadMore;
	}

	public void LoadMoreFinish(){
		mIsLoadMore = false;
	}
}
