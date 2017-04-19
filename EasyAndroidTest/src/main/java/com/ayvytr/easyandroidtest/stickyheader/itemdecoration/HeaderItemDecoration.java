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

        int left = 0;
        int right = parent.getWidth();//如果要考虑padding，则应减去左右padding
        int preGroupId = -1;
        int groupId = -1;
        int headHeight = 0;
        for(int i = 0; i < childCount; i++)
        {
//            View childView = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(childView);
//            Rect rect = new Rect();
//            parent.getDecoratedBoundsWithMargins(childView, rect);
//            if(hasHeader(position))
//            {
//                c.translate(parent.getPaddingLeft(), rect.top);
//                getHeaderView(parent, position).draw(c);
//                c.translate(-parent.getPaddingLeft(), -rect.top);
//            }
            View itemView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(itemView);
            if(position == RecyclerView.NO_POSITION)
            {
                continue;
            }
            //只有各组第一个 并且 groupId!=-1 才绘制头部view
            preGroupId = groupId;
            groupId = headerAdapter.getId(position);
            if(groupId == -1 || groupId == preGroupId) continue;
            View header = headerViewCache.getHeader(parent, position);
            //获取缓存的头部view的位置信息，如果没有则新创建
            //获取头部view的top、bottom位置信息
            float textY = itemView.getTop();
            headHeight = header.getHeight();
            if(true)
            {
                textY = Math.max(headHeight, itemView.getTop());
                int nextPosition = getNextGroupId(i, groupId, childCount, parent);
                if(nextPosition != -1)
                {
                    View itemView1 = parent.getChildAt(nextPosition);
                    //判断下一个头部view是否到了与上一个头部view接触的临界值
                    //如果满足条件则把上一个头部view推上去
                    if(itemView1.getTop() <= headHeight + header.getHeight())
                    {
                        textY = itemView1.getTop() - header.getHeight();
                    }
                }
            }
            //绘制头部view
            Rect rect = new Rect();
            rect.set(left, (int) textY - headHeight, right, (int) textY);
            c.translate(left, textY - headHeight);
//            drawHeader(c, header, headerOffset);
            itemView.draw(c);
        }
    }

    private int getNextGroupId(int id, int groupId, int childCount, RecyclerView parent)
    {
        for(int i = id; i < childCount; i++)
        {
            if(headerAdapter.getId(parent.getChildAdapterPosition(parent.getChildAt(i))) != groupId)
            {
                return i;
            }
        }
        return -1;
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
        int id = headerAdapter.getId(position);
        if(position == 0)
        {
            return id >= 0;
        }

        int preId = headerAdapter.getId(position - 1);
        return id >= 0 && id != preId;
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
