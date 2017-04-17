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

public class Custom2ItemDecoration extends RecyclerView.ItemDecoration
{
    private StickyItemHeaderAdapter headerAdapter;

    public Custom2ItemDecoration(StickyItemHeaderAdapter headerAdapter)
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
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
