package com.ayvytr.easyandroidlibrary.view.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * 自定义PopupWindow，设置了必要的属性，包括：
 * 背景颜色(设置背景色才有效）
 * 按返回键关闭
 * 点击非PopupWindow区域自动关闭
 * 打开关闭动画.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.1
 */
public class BasePopupWindow extends PopupWindow
{
    protected View view;
    protected View parent;
    protected Context context;

    public BasePopupWindow(Context context, @LayoutRes int resource, @ColorInt int color,
                           int animationStyle, View parent)
    {
        super(context);
        this.context = context;
        this.parent = parent;
        view = LayoutInflater.from(context).inflate(resource, null);
        setContentView(view);

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchInterceptor(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        update();
        //要为popWindow设置一个背景才有效
        setBackgroundDrawable(new ColorDrawable(color));

        setAnimationStyle(animationStyle);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setParent(View parent)
    {
        this.parent = parent;
    }

    public void showAsFullScreen()
    {
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        if(!isShowing())
        {
            showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
        }
        else
        {
            dismiss();
        }
    }

    /**
     * 显示或隐藏PopupWindow
     *
     * @param isCenter 是否居中显示，如果为true，忽略isLeft, isTop
     * @param isLeft   是否在左侧
     * @param isTop    是否在顶部
     * @param x        x坐标
     * @param y        y坐标
     */
    public void show(boolean isCenter, boolean isLeft, boolean isTop, int x, int y)
    {
        if(!isShowing())
        {
            if(isCenter)
            {
                showAtLocation(parent, Gravity.CENTER, x, y);
            }
            else
            {
                showAtLocation(parent,
                        (isLeft ? Gravity.LEFT : Gravity.RIGHT) | (isTop ? Gravity.TOP : Gravity.BOTTOM),
                        x, y);
            }
        }
        else
        {
            dismiss();
        }
    }

    public void show()
    {
        if(!isShowing())
        {
            showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
        else
        {
            dismiss();
        }
    }
}
