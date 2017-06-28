package com.baidu.weiwei22.personalresearch.recycle;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.weiwei22.personalresearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwei22 on 17/6/27.
 */

public class RecycleFragment extends Fragment {
    private TextView mBtn;
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
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
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutManager.setOrientation(mLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                mItemDecoration.setOrientation(mLayoutManager.getOrientation());
                mRecycleView.addItemDecoration(mItemDecoration);
                mRecycleView.requestLayout();
            }
        });

        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recycle_view);
        mLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
//        mRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 4, LinearLayoutManager.HORIZONTAL, true));
        mRecycleView.setLayoutManager(mLayoutManager);
//        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        mAdapter = new RecyclePinterestAdapter(getContext());
        mRecycleView.setAdapter(mAdapter);

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

        mRecycleView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                Log.e("DAVID", "onChildViewAttachedToWindow view = " + view);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Log.e("DAVID", "-----onChildViewDetachedFromWindow view = " + view);
            }
        });

        mRecycleView.

//        mItemDecoration = new MyItemDecoration(getContext());
//        mItemDecoration.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecycleView.addItemDecoration(mItemDecoration);

        return rootView;
    }

    private static class RecyclePinterestAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

        private Context mContext;
        private List<String> mData = new ArrayList<>();
        private int[] mHeights = new int[30];
        private int[] mColors = new int[30];

        public RecyclePinterestAdapter(Context context) {
            mContext = context;
            for (int i = 0; i < 30; i++) {
                mData.add("数据---" + i);
                mHeights[i] = (int)(200 + Math.random() * 400);
                String c1 = Integer.toHexString((int)(Math.random() * 255));
                String c2 = Integer.toHexString((int)(Math.random() * 255));
                String c3 = Integer.toHexString((int)(Math.random() * 255));
                String color = "#" + (c1.length() == 2 ? c1 : c1 + "0") + (c2.length() == 2 ? c2 : c2 + "0") + (c3.length() == 2 ? c3 : c3 + "0");
                mColors[i] = Color.parseColor(color);
            }
        }

        @Override
        public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
            return new RecycleViewHolder(holderView);
        }

        @Override
        public void onBindViewHolder(RecycleViewHolder holder, final int position) {
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
        public int getItemCount() {
            return mData.size();
        }
    }

    /**
     * 不同的ViewType
     */
    private static class RecycleViewAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private List<String> mData = new ArrayList<>();

        public RecycleViewAdapter(Context context) {
            mContext = context;
            for (int i = 0; i < 30; i++) {
                mData.add("数据---" + i);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("David-----------", "onCreateViewHolder parent = " + parent.getId());
            View holderView = null;
            if (0 == viewType) {
                holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
                return new RecycleViewHolder(holderView);
            } else if (1 == viewType) {
                holderView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_2, parent, false);
                return new RecycleViewHolder2(holderView);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int viewType = getItemViewType(position);
            if (0 == viewType) {
                ((RecycleViewHolder)holder).bindData(position, mData.get(position));
            } else if (1 == viewType) {
                ((RecycleViewHolder2)holder).bindData(position, mData.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? 0 : 1;
        }
    }

    private static class RecycleViewHolder extends RecyclerView.ViewHolder {
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

    private static class RecycleViewHolder2 extends RecyclerView.ViewHolder {
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
    private static class MyItemDecoration extends RecyclerView.ItemDecoration {
        private int mOrientation = LinearLayoutManager.VERTICAL;
        private Drawable mDividerDrawable;

        public MyItemDecoration(Context context) {
            mDividerDrawable = new BitmapDrawable(BitmapFactory.decodeResource(context.getResources(), R.mipmap.recycler_divider));
        }

        public void setOrientation(int orientation) {
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
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
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
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
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + mDividerDrawable.getIntrinsicHeight();

                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(canvas);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, mDividerDrawable.getIntrinsicHeight());
            } else {
                // TODO 横向时第一个Divider和第二个View交接的位置UI有问题
                outRect.set(0, 0, mDividerDrawable.getIntrinsicHeight(), 0);
            }
        }
    }
}
