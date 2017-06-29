package com.baidu.weiwei22.personalresearch.recycler_view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.weiwei22.personalresearch.R;
import com.baidu.weiwei22.personalresearch.wapped_common.WrappedRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ItemDecoration;
import static android.support.v7.widget.RecyclerView.LayoutManager;
import static android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import static android.support.v7.widget.RecyclerView.OnClickListener;
import static android.support.v7.widget.RecyclerView.State;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by weiwei22 on 17/6/27.
 */

public class RecycleFragment extends Fragment {
    private TextView mBtn, mBtn1;
    private RecyclerView mRecycleView;
    private WrappedRecyclerAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    MyItemDecoration mItemDecoration;

    public static RecycleFragment getInstance() {
        RecycleFragment fragment = new RecycleFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("David-----------", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("David-----------", "onCreateView container = " + container.getId());
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.frag_recycleview, null);
        Log.e("David-----------", "onCreateView rootView = " + rootView.getId() + ", rootView.getParent = " + rootView.getParent());

        mBtn = (TextView) rootView.findViewById(R.id.btn);
        mBtn1 = (TextView) rootView.findViewById(R.id.btn1);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mLayoutManager.setOrientation(mLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                mItemDecoration.setOrientation(mLayoutManager.getOrientation());
                mRecycleView.addItemDecoration(mItemDecoration);
                mRecycleView.requestLayout();*/
                Log.e("Dbn", "onClick-----");
//                ((RecyclePinterestAdapter)mAdapter.getAdapter()).addData("Item ---- New");
//                mAdapter.notifyItemInserted(1);
//                addHeaderFooter(2, (WrappedRecyclerAdapter) mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        });

        mBtn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((WrappedRecyclerAdapter)mAdapter).removeHeaderView(mH);
//                mAdapter.notifyDataSetChanged();
//                ((RecyclePinterestAdapter)mAdapter.getAdapter()).removeData();
//                mAdapter.notifyItemRemoved(1);
            }
        });

        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recycle_view);
//        mLayoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
//        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        mAdapter = (WrappedRecyclerAdapter) getWrappedAdapter(new RecyclePinterestAdapter(getContext()));
        SlideInLeftAnimationAdapter aa = new SlideInLeftAnimationAdapter(mAdapter);
        aa.setDuration(300);
        mRecycleView.setAdapter(aa);
//        mRecycleView.setItemAnimator(new SlideInUpAnimator());

        mRecycleView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e("DAVID", "onScrollStateChanged newState = " + newState);
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.e("DAVID", "onScrolled dx = " + dx + ", dy = " + dy);
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecycleView.addOnChildAttachStateChangeListener(new OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                Log.e("DAVID", "onChildViewAttachedToWindow view = " + view);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Log.e("DAVID", "-----onChildViewDetachedFromWindow view = " + view);
            }
        });

//        mItemDecoration = new MyItemDecoration(getContext());
//        mItemDecoration.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecycleView.addItemDecoration(mItemDecoration);

        return rootView;
    }

    private RecyclerView.Adapter getWrappedAdapter(RecyclerView.Adapter adapter) {
        WrappedRecyclerAdapter wrappedRecyclerAdapter = new WrappedRecyclerAdapter(adapter);

        addHeaderFooter(1, wrappedRecyclerAdapter);

        return wrappedRecyclerAdapter;
    }

    RecycleViewHolder mH;
    private void addHeaderFooter(int index, WrappedRecyclerAdapter wrappedRecyclerAdapter) {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.item_recycle, null);
        RecycleViewHolder holder = new RecycleViewHolder(headerView);
        holder.setLayoutParams(getContext().getResources().getDisplayMetrics().widthPixels, index == 1 ? 400 : 2000, index == 1 ? Color.CYAN : Color.RED);
        holder.bindData(0, "我是Header");
        wrappedRecyclerAdapter.addHeaderView(holder);
        mH = holder;

        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.item_recycle_2, null);
        RecycleViewHolder2 holder2 = new RecycleViewHolder2(footerView);
        holder2.bindData(101, "我是Footer");
        wrappedRecyclerAdapter.addFooterView(holder2);
    }

    /**
     * 瀑布流Adapter
     */
    private static class RecyclePinterestAdapter extends RecyclerView.Adapter {

        private List<String> mData = new ArrayList<>();
        private int[] mHeights = new int[30];
        private int[] mColors = new int[30];

        private Context mContext;

        public RecyclePinterestAdapter(Context context) {
            mContext = context;

            for (int i = 0; i < 25; i++) {
                mData.add("数据---" + i);
            }
            for (int i = 0; i < 30; i++) {
                mHeights[i] = (int) (200 + Math.random() * 400);
                String c1 = Integer.toHexString((int) (Math.random() * 255));
                String c2 = Integer.toHexString((int) (Math.random() * 255));
                String c3 = Integer.toHexString((int) (Math.random() * 255));
                String color = "#" + (c1.length() == 2 ? c1 : c1 + "0") + (c2.length() == 2 ? c2 : c2 + "0") + (c3.length() == 2 ? c3 : c3 + "0");
                mColors[i] = Color.parseColor(color);
            }
        }

        private void addData(String msg) {
            mData.add(0, msg);
        }

        private void removeData() {
            mData.remove(0);
        }

        @Override
        public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
            return new RecycleViewHolder(holderView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder h, final int position) {
            RecycleViewHolder holder = (RecycleViewHolder) h;
            holder.setLayoutParams(mContext.getResources().getDisplayMetrics().widthPixels, mHeights[position], mColors[position]);
            holder.bindData(position, mData.get(position));
            holder.setonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mData.get(position), Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


    //=============
    /*private static class InheritanceAdapter extends WrappedRecyclerAdapter {
        private Context mContext;
        private List<String> mData = new ArrayList<>();

        public InheritanceAdapter(Context context) {
            Log.e("InheritanceAdapter", "RecycleViewAdapter");
            mContext = context;
            for (int i = 0; i < 101; i++) {
                mData.add("数据---" + i);
            }

            View headerView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, null);
            addHeaderView(headerView);
            View footerView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_2, null);
            addFooterView(footerView);
        }

        @Override
        public RecycleViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType, View view) {
            Log.e("WrappedRecyclerAdapter", "onCreateHeaderViewHolder view = " + view);
            RecycleViewHolder holder = new RecycleViewHolder(view);
            holder.setLayoutParams(mContext.getResources().getDisplayMetrics().widthPixels, 400, Color.CYAN);
            holder.bindData(0, "我是Header");
            return holder;
        }

        @Override
        public RecycleViewHolder2 onCreateFooterViewHolder(ViewGroup parent, int viewType, View view) {
            Log.e("WrappedRecyclerAdapter", "onCreateFooterViewHolder view = " + view);
            RecycleViewHolder2 holder = new RecycleViewHolder2(view);
//            holder.setLayoutParams(mContext.getResources().getDisplayMetrics().widthPixels, 220, Color.RED);
            holder.bindData(101, "我是Footer");
            return holder;
        }

        @Override
        public RecycleViewHolder onCreateViewHolderN(ViewGroup parent, int viewType) {
            View holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
            return new RecycleViewHolder(holderView);
        }

        @Override
        public void onBindViewHolderN(ViewHolder holder, int position) {
            ((RecycleViewHolder)holder).bindData(position, mData.get(position));
        }

        @Override
        public int getItemViewTypeN(int position) {
            return NORMAL_VIEW_TYPE;
        }

        @Override
        public int getItemCountN() {
            return mData.size();
        }
    }*/

    //=============


    /**
     * 不同的ViewType
     */
    private static class RecycleViewAdapter extends Adapter {
        private Context mContext;
        private List<String> mData = new ArrayList<>();
        private View mHeaderView;

        public RecycleViewAdapter(Context context) {
            Log.e("D1AVID", "RecycleViewAdapter");
            mContext = context;
            for (int i = 0; i < 120; i++) {
                mData.add("数据---" + i);
            }

            mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_2, null);
            RecycleViewHolder2 holder2 = new RecycleViewHolder2(mHeaderView);
            holder2.bindData(10, "我是Header");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("David-----------", "onCreateViewHolder parent = " + parent.getId());
            View holderView = null;
            if (0 == viewType) {
                holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
                return new RecycleViewHolder(holderView);
            } else if (1 == viewType) {
//                holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_2, parent, false);
                return new RecycleViewHolder2(mHeaderView);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int viewType = getItemViewType(position);
            if (0 == viewType) {
                ((RecycleViewHolder)holder).bindData(position, mData.get(getRealPosition(holder)));
            } else if (1 == viewType) {
                return;//((RecycleViewHolder2)holder).bindData(position, mData.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mHeaderView != null ? mData.size() + 1 : mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 1 : 0;
        }

        private int getRealPosition(ViewHolder holder) {
            int pos = holder.getLayoutPosition();
            return mHeaderView == null ? pos : pos - 1;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            final LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                Log.e("D1AVID", "onAttachedToRecyclerView");
                ((GridLayoutManager)layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position) == 1 ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
                if (holder.getLayoutPosition() == 0) {
                    ((StaggeredGridLayoutManager.LayoutParams) params).setFullSpan(true);
                }
            }
        }
    }

    private static class RecycleViewHolder extends ViewHolder {
        private TextView mTv;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tv);
        }

        public void setonClickListener(View.OnClickListener listener) {
            mTv.setOnClickListener(listener);
        }

        public void setLayoutParams(int width, int height, int color) {
            CardView.LayoutParams params = (CardView.LayoutParams) mTv.getLayoutParams();
            params.width = width;
            params.height = height;
            mTv.setLayoutParams(params);
            mTv.setBackgroundColor(color);
        }

        public void bindData(int position, String data) {
//            Log.e("David-----------1", "bindData position = " + position + ", mTv = " + mTv);
            mTv.setText(data);
        }
    }

    private static class RecycleViewHolder2 extends ViewHolder {
        private TextView mTv1;
        private TextView mTv2;

        public RecycleViewHolder2(View itemView) {
            super(itemView);
            mTv1 = (TextView) itemView.findViewById(R.id.tv1);
            mTv2 = (TextView) itemView.findViewById(R.id.tv2);
        }

        public void bindData(int position, String data) {
            mTv1.setText(data);
            mTv2.setText("临时数据");
        }
    }

    /**
     * 分割线
     */
    private static class MyItemDecoration extends ItemDecoration {
        private int mOrientation = LinearLayoutManager.VERTICAL;
        private Drawable mDividerDrawable;

        public MyItemDecoration(Context context) {
            mDividerDrawable = new BitmapDrawable(BitmapFactory.decodeResource(context.getResources(), R.mipmap.recycler_divider));
        }

        public void setOrientation(int orientation) {
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, State state) {
            if (LinearLayoutManager.HORIZONTAL == mOrientation) {
                drawDividerHorizontal(c, parent);
            } else if (LinearLayoutManager.VERTICAL == mOrientation) {
                drawDividerVertical(c, parent);
            }
        }

        private void drawDividerVertical(Canvas canvas, RecyclerView parent) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int count = parent.getChildCount();

            for (int i = 0; i < count; i++) {
                View child = parent.getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDividerDrawable.getIntrinsicHeight();

                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(canvas);
            }
        }

        private void drawDividerHorizontal(Canvas canvas, RecyclerView parent) {
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() - parent.getPaddingBottom();
            int count = parent.getChildCount();

            for (int i = 0; i < count; i++) {
                View child = parent.getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + mDividerDrawable.getIntrinsicHeight();

                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(canvas);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, mDividerDrawable.getIntrinsicHeight());
            } else {
                // TODO 横向时第一个Divider和第二个View交接的位置UI有问题
                outRect.set(0, 0, mDividerDrawable.getIntrinsicHeight(), 0);
            }
        }
    }
}
