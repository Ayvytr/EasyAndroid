package com.ayvytr.easyandroidtest.stickyheader.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Desc:
 * Date: 2017/4/17
 *
 * @author davidwang
 */

public class HeaderItemDecoration extends RecyclerView.ItemDecoration
{
    private StickyItemHeaderAdapter headerAdapter;
    private HeaderViewCache headerViewCache;

    public HeaderItemDecoration(StickyItemHeaderAdapter headerAdapter)
    {
        this.headerAdapter = headerAdapter;
        headerViewCache = new HeaderViewCache(headerAdapter);
        ((RecyclerView.Adapter) headerAdapter)
                .registerAdapterDataObserver(new RecyclerView.AdapterDataObserver()
                {
                    @Override
                    public void onChanged()
                    {
                        headerViewCache.invalidate();
                    }
                });
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        super.onDrawOver(c, parent, state);
        draw(c, parent);
    }

    private void draw(Canvas c, RecyclerView parent)
    {
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++)
        {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);
            Rect rect = new Rect();
            parent.getDecoratedBoundsWithMargins(childView, rect);
            if(hasHeader(position))
            {
                c.translate(0, rect.top);
                getHeaderView(parent, position).draw(c);
                c.translate(0, -rect.top);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        try
        {
            if(hasHeader(position))
            {
                int height = getHeaderView(parent, position).getHeight();
                outRect.set(0, height, 0, 0);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean hasHeader(int position)
    {
        return headerAdapter.getId(position) == position;
    }

    /**
     * 获取HeaderView
     *
     * @param parent
     * @param position RecyclerView当前Item的position，需要用 {@link RecyclerView#getChildAdapterPosition(View)}
     *                 获得真实position，不然显示的数据有误
     * @return Header view
     */
    public View getHeaderView(RecyclerView parent, int position)
    {
        return headerViewCache.getHeader(parent, position);
    }
}
