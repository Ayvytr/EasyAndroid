package com.ayvytr.easyandroid.view.popwindow;

import android.app.Activity;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;

/**
 * PopupWindow显示时，PopupWindow外的区域显示为半透明.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.1
 */

public class AlphaPopupWindow extends BasePopupWindow
{
    protected Activity activity;
    private float alpha = 0.5f;

    public AlphaPopupWindow(Activity activity, @LayoutRes int resource,
                            @ColorInt int color, int animationStyle)
    {
        super(activity, resource, color, animationStyle, activity.getWindow().getDecorView());
        this.activity = activity;
    }

    /**
     * 设置PopupWindow外显示区域的透明度 0-1
     *
     * @param alpha 透明度
     */
    public void setAlpha(float alpha)
    {
        if(alpha <= 0)
        {
            alpha = 0.5f;
        }
        else if(alpha >= 1)
        {
            alpha = 0.8f;
        }

        this.alpha = alpha;
    }

    @Override
    public void dismiss()
    {
        super.dismiss();
        setBackgroundAlpha(activity, 1f);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y)
    {
        super.showAtLocation(parent, gravity, x, y);
        setBackgroundAlpha(activity, alpha);
    }

    @Override
    public void showAsFullScreen()
    {
        super.showAsFullScreen();
        setBackgroundAlpha(activity, alpha);
    }

    @Override
    public void showAsDropDown(View anchor)
    {
        super.showAsDropDown(anchor);
        setBackgroundAlpha(activity, alpha);
    }

    /**
     * 设置页面的透明度
     *
     * @param activity 目标activity
     * @param bgAlpha  1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha)
    {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if(bgAlpha == 1)
        {
            activity.getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        }
        else
        {
            activity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }
}
