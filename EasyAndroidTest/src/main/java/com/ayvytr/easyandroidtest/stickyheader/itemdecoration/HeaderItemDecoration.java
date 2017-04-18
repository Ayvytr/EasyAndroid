package com.ayvytr.easyandroidtest.stickyheader.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ayvytr.logger.L;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Date: 2017/4/17
 *
 * @author davidwang
 */

public class HeaderItemDecoration extends RecyclerView.ItemDecoration
{
    private StickyItemHeaderAdapter headerAdapter;

    private Map<Integer, View> viewMap = new HashMap<>();

    public HeaderItemDecoration(StickyItemHeaderAdapter headerAdapter)
    {
        this.headerAdapter = headerAdapter;
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
            Rect rect = new Rect();
            parent.getDecoratedBoundsWithMargins(childView, rect);
            L.e(i, rect.toShortString(), rect.top);
            c.translate(0, rect.top);
            getHeaderView(parent, i).draw(c);
        }
    }

    private void measureHeader(View itemView, RecyclerView parent)
    {
        int widthSpec = View.MeasureSpec
                .makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec
                .makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);

        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(),
                itemView.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(),
                itemView.getLayoutParams().height);
        itemView.measure(childWidth, childHeight);
        itemView.layout(0, 0, itemView.getMeasuredWidth(), itemView.getMeasuredHeight());
    }

//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
//                               RecyclerView.State state)
//    {
//        super.getItemOffsets(outRect, view, parent, state);
//        int position = parent.getChildAdapterPosition(view);
//        try
//        {
//            int height = getHeaderView(parent, position).getHeight();
//            outRect.set(0, height, 0, 0);
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    private View getHeaderView(RecyclerView parent, int position)
    {
        View view = viewMap.get(position);
        if(view != null)
        {
            return view;
        }

        if(!headerAdapter.isHeader(position))
        {
            return null;
        }

        RecyclerView.ViewHolder viewHolder = headerAdapter.onCreateHeaderViewHolder(parent);
        headerAdapter.onBindHeaderViewHolder(viewHolder, position);
        measureHeader(viewHolder.itemView, parent);
        viewMap.put(position, viewHolder.itemView);
        return viewHolder.itemView;
    }
}
