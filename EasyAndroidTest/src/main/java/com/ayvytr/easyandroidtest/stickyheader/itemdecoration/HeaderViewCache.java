package com.ayvytr.easyandroidtest.stickyheader.itemdecoration;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Desc:
 * Date: 2017/4/18
 *
 * @author davidwang
 */

class HeaderViewCache
{
    private final StickyHeaderAdapter adapter;
    private final SparseArray<View> mHeaderViews = new SparseArray<>();//缓存头部View

    public HeaderViewCache(StickyHeaderAdapter adapter)
    {
        this.adapter = adapter;
    }

    public View getHeader(RecyclerView parent, int position)
    {
        int headerId = adapter.getId(position);
        View header = mHeaderViews.get(headerId);
        //判断缓存里是否有
        if(header == null)
        {
            //获取头部View
            RecyclerView.ViewHolder viewHolder = adapter.onCreateHeaderViewHolder(parent);
            adapter.onBindHeaderViewHolder(viewHolder, position);
            header = viewHolder.itemView;
            //测量view的宽高，相当于onMeasure
            ViewGroup.LayoutParams lp = header.getLayoutParams();
            if(lp == null)
            {
                header.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            int widthSpec;
            int heightSpec;
            widthSpec = View.MeasureSpec
                    .makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
            heightSpec = View.MeasureSpec
                    .makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);
            //获取view的宽高，可以考虑padding，这里宽度我实现的效果不能考虑padding，如果需要，可以参考高度的实现
            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, 0, lp.width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(), lp.height);
            header.measure(childWidth, childHeight);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
            mHeaderViews.put(headerId, header);
        }
        return header;
    }

    /**
     * 清空缓存，当数据刷新时可以得到更新后的view
     */
    public void invalidate()
    {
        mHeaderViews.clear();
    }
}
