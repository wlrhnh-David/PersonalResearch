package com.baidu.weiwei22.personalresearch.wapped_common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiwei22 on 17/6/28.
 */

public class WrappedRecyclerAdapter extends RecyclerView.Adapter {
    private static final int HEADER_VIEW_TYPE = 1;
    private static final int FOOTER_VIEW_TYPE = 2;

    private Map<RecyclerView.ViewHolder, Boolean> mHeaderViewMap = new HashMap<>();
    private Map<RecyclerView.ViewHolder, Boolean> mFooterViewMap = new HashMap<>();

    private RecyclerView.Adapter mAdapter;

    public WrappedRecyclerAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    /**
     * 添加头部ViewHolder，可以添加多个
     *
     * @param viewHolder
     */
    public void addHeaderView(RecyclerView.ViewHolder viewHolder) {
        if (!mHeaderViewMap.containsKey(viewHolder)) {
            mHeaderViewMap.put(viewHolder, true);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(RecyclerView.ViewHolder viewHolder) {
        if (mHeaderViewMap.containsKey(viewHolder)) {
            mHeaderViewMap.remove(viewHolder);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部ViewHolder，可以添加多个
     *
     * @param viewHolder
     */
    public void addFooterView(RecyclerView.ViewHolder viewHolder) {
        if (!mFooterViewMap.containsKey(viewHolder)) {
            mFooterViewMap.put(viewHolder, true);
            notifyDataSetChanged();
        }
    }

    public void removeFooterView(RecyclerView.ViewHolder viewHolder) {
        if (mFooterViewMap.containsKey(viewHolder)) {
            mFooterViewMap.remove(viewHolder);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取第一个可用的Header或者Footer ViewHolder，同时将value置为true，说明已经不可用了
     * @param map
     * @return
     */
    private RecyclerView.ViewHolder getFirstValidViewHolder(Map<RecyclerView.ViewHolder, Boolean> map) {
        for (Map.Entry<RecyclerView.ViewHolder, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                entry.setValue(false);
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW_TYPE && !mHeaderViewMap.isEmpty()) {
            return getFirstValidViewHolder(mHeaderViewMap);
        } else if (viewType == FOOTER_VIEW_TYPE && !mFooterViewMap.isEmpty()) {
            return getFirstValidViewHolder(mFooterViewMap);
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        return holder.getLayoutPosition() - mHeaderViewMap.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER_VIEW_TYPE || getItemViewType(position) == FOOTER_VIEW_TYPE) {
            return;
        }
        mAdapter.onBindViewHolder(holder, getRealPosition(holder));
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mHeaderViewMap.size()) {
            return HEADER_VIEW_TYPE;
        } else if (position >= mHeaderViewMap.size() + mAdapter.getItemCount()) {
            return FOOTER_VIEW_TYPE;
        } else {
            return mAdapter.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        int totalCount = mAdapter.getItemCount() + mHeaderViewMap.size() + mFooterViewMap.size();
        return totalCount;
    }

    /**
     * 处理LayoutManager为GridLayoutManager的特殊情况，让HeaderView或者FooterView占整行。
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == HEADER_VIEW_TYPE || getItemViewType(position) == FOOTER_VIEW_TYPE) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 处理LayoutManager为StaggeredLayoutManager的特殊情况，让HeaderView或者FooterView占整行。
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        final ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
            if (holder.getItemViewType() == HEADER_VIEW_TYPE || holder.getItemViewType() == FOOTER_VIEW_TYPE) {
                ((StaggeredGridLayoutManager.LayoutParams) params).setFullSpan(true);
            }
        }
    }
}
