package com.ayvytr.easyandroid.view.recyclerview.itemdecoration;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ayvytr.easyandroid.tools.Colors;

/**
 * {@link RecyclerView}的{@link RecyclerView#addItemDecoration(RecyclerView.ItemDecoration)}，支持
 * {@link LinearLayoutManager}, {@link GridLayoutManager}, 可自定义方向，颜色，间隔线宽度.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.8
 */

public class PrettyItemDecoration extends RecyclerView.ItemDecoration
{
    private static final int DEFAULT_WIDTH = 1;
    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    private Paint paint;
    //Divider宽度，像素
    private int dividerWidth = 1;
    //Divider方向
    private int orientation = DEFAULT_WIDTH;
    @ColorInt
    private int color;

    public PrettyItemDecoration()
    {
        this(OrientationHelper.HORIZONTAL);
    }

    public PrettyItemDecoration(int orientation)
    {
        this(orientation, Colors.AERO);
    }

    public PrettyItemDecoration(int orientation, @ColorInt int color)
    {
        this(orientation, color, DEFAULT_WIDTH);
    }

    public PrettyItemDecoration(int orientation, @ColorInt int color, int dividerWidth)
    {
        this.orientation = orientation;
        this.color = color;
        this.dividerWidth = dividerWidth;
        initPaint();
    }

    private void initPaint()
    {
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(this.dividerWidth);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        super.onDrawOver(c, parent, state);
        if(parent.getLayoutManager() == null)
        {
            return;
        }

        if(orientation == OrientationHelper.HORIZONTAL)
        {
            drawHorizontal(c, parent);
        }
        else
        {
            drawVertical(c, parent);
        }
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas c, RecyclerView parent)
    {
        if(parent.getClipToPadding())
        {
            float suitableDividerWidth = getDividerOffset();
            c.clipRect(parent.getPaddingLeft() - suitableDividerWidth,
                    parent.getPaddingTop() - suitableDividerWidth,
                    parent.getWidth() - parent.getPaddingRight() + suitableDividerWidth,
                    parent.getHeight() - parent.getPaddingBottom() + suitableDividerWidth);
        }

        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++)
        {
            View view = parent.getChildAt(i);
            Rect rect = new Rect();
            parent.getDecoratedBoundsWithMargins(view, rect);
            c.drawLine(rect.left, rect.top, rect.left, rect.bottom, paint);
        }

        int itemDividerWidth = getItemDividerWidth(parent);
        int spanCount = getSpanCount(parent);

        if(parent.getClipToPadding())
        {
            for(int i = spanCount - 1; i < childCount; i += spanCount)
            {
                View view = parent.getChildAt(i);
                Rect rect = new Rect();
                parent.getDecoratedBoundsWithMargins(view, rect);
                int left = rect.left + itemDividerWidth;
                c.drawLine(left, rect.top, left, rect.bottom, paint);
            }
        }

        View view = parent.getChildAt(parent.getChildCount() - 1);
        Rect rect = new Rect();
        parent.getDecoratedBoundsWithMargins(view, rect);
        int left = rect.left + itemDividerWidth;
        c.drawLine(left, rect.top, left, rect.bottom, paint);
    }

    private int getSpanCount(RecyclerView parent)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager)
        {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        else if(layoutManager instanceof StaggeredGridLayoutManager)
        {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        else if(layoutManager instanceof LinearLayoutManager)
        {
            int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            if(orientation == LinearLayoutManager.HORIZONTAL)
            {
                return parent.getChildCount();
            }
        }

        return 1;
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas c, RecyclerView parent)
    {
        if(parent.getClipToPadding())
        {
            float suitableDividerWidth = getDividerOffset();
            c.clipRect(parent.getPaddingLeft() - suitableDividerWidth,
                    parent.getPaddingTop() - suitableDividerWidth,
                    parent.getWidth() - parent.getPaddingRight() + suitableDividerWidth,
                    parent.getHeight() - parent.getPaddingBottom() + suitableDividerWidth);
        }

        int itemDividerWidth = getItemDividerWidth(parent);
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++)
        {
            View view = parent.getChildAt(i);
            Rect rect = new Rect();
            parent.getDecoratedBoundsWithMargins(view, rect);
            int right = rect.left + itemDividerWidth;
            c.drawLine(rect.left, rect.top, right, rect.top, paint);
        }

        int spanCount = getSpanCount(parent);
        if(spanCount >= 0)
        {
            for(int i = childCount - spanCount; i < childCount; i++)
            {
                View view = parent.getChildAt(i);
                Rect rect = new Rect();
                parent.getDecoratedBoundsWithMargins(view, rect);
                int right = rect.left + itemDividerWidth;
                c.drawLine(rect.left, rect.bottom, right, rect.bottom, paint);
            }
        }
    }

    private float getDividerOffset()
    {
        return dividerWidth / 2f;
    }

    private int getItemDividerWidth(RecyclerView parent)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int width = layoutManager.getWidth() - layoutManager.getPaddingLeft() - layoutManager
                .getPaddingRight();
        if(layoutManager instanceof GridLayoutManager)
        {
            return width / ((GridLayoutManager) layoutManager).getSpanCount();
        }
        else if(layoutManager instanceof StaggeredGridLayoutManager)
        {
            return width / ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }

        return width;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        if(orientation == OrientationHelper.HORIZONTAL)
        {
            outRect.set(0, 0, 0, dividerWidth);
        }
        else
        {
            outRect.set(0, 0, dividerWidth, 0);
        }
    }
}
