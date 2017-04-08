package com.ayvytr.easyandroid.view.popwindow;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.Gravity;

import com.ayvytr.easyandroid.tools.withcontext.BarTool;
import com.ayvytr.easyandroid.tools.withcontext.DensityTool;


/**
 * 在顶部显示的PopupWindow。可以定位在顶部的左，中，右侧.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.1
 */
public class TopPopupWindow extends AlphaPopupWindow
{

    private int toolBarHeight;

    public TopPopupWindow(Activity activity, @LayoutRes int resource, @ColorInt int color,
                          int animationStyle)
    {
        super(activity, resource, color, animationStyle);
        initToolBarHeight();
    }

    private void initToolBarHeight()
    {
        toolBarHeight = BarTool.getActionBarHeight(activity) + BarTool
                .getStatusBarHeight(activity) + DensityTool.dp2px(5);
    }

    /**
     * 显示在顶部
     *
     * @param isCenter      {@code true} PopupWindow居中显示，忽略 isLeft参数<br>
     *                      {@code false} PopupWindow不居中显示，isLeft参数生效
     * @param isLeft        {@code true} PopupWindow在左边显示<br>
     *                      {@code false} PopupWindow在右边显示
     * @param coverTitleBar 是否覆盖标题栏
     */
    public void showTop(boolean isCenter, boolean isLeft, boolean coverTitleBar)
    {
        int y = coverTitleBar ? toolBarHeight : 0;

        if(!isShowing())
        {
            if(isCenter)
            {
                showAtLocation(view, Gravity.TOP | Gravity.CENTER, 0, y);
            }
            else
            {
                showAtLocation(view, Gravity.TOP | (isLeft ? Gravity.LEFT : Gravity.RIGHT), 20, y);
            }
        }
        else
        {
            dismiss();
        }
    }

    /**
     * 显示在右上角，留出状态栏和标题栏高度
     */
    public void showTopRight()
    {
        if(!isShowing())
        {
            showTop(false, false, true);
        }
        else
        {
            dismiss();
        }
    }
}
