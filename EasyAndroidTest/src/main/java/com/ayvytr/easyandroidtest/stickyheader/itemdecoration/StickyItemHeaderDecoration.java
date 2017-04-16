package com.ayvytr.easyandroidtest.stickyheader.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ayvytr.easyandroid.tools.Colors;

/**
 * Desc.
 *
 * @author Doing
 * @since 1.0.0
 */

public class StickyItemHeaderDecoration extends RecyclerView.ItemDecoration
{
    private Context context;
    private RecyclerView.Adapter adapter;

    public StickyItemHeaderDecoration(Context context)
    {
        this.context = context;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        super.onDrawOver(c, parent, state);
        onDrawWithOrientation(c, parent, state);
    }

    private void onDrawWithOrientation(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        int orientation = getOrientation(parent);
        if(orientation == -1)
        {
            return;
        }

        Paint paint = new Paint();
        paint.setColor(Colors.ACID_GREEN);
        paint.setTextSize(50);
        c.drawText("A", 50, 50, paint);
        c.drawRect(0, 0, 50, 50, paint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 50, 50);
    }

    private int getOrientation(RecyclerView parent)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager)
        {
            return ((LinearLayoutManager) layoutManager).getOrientation();
        }

        return -1;
    }
}
