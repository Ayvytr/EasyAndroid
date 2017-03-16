package com.ayvytr.easyandroidlibrary.tools.withcontext;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.ayvytr.easyandroidlibrary.Easy;
import com.ayvytr.easyandroidlibrary.exception.UnsupportedInitializationException;

/**
 * 获取资源中Drawable，String，dimension，color, Configuration.
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class ResTool
{
    private ResTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 从资源中获取Drawable
     */
    public static Drawable getDrawable(@DrawableRes int id)
    {
        //使用ContextCompat应是谷歌推荐的
        return ContextCompat.getDrawable(Easy.getContext(), id);
    }

    /**
     * 从资源中获取String
     */
    public static String getString(@StringRes int id)
    {
        return Easy.getContext().getString(id);
    }

    /**
     * 从资源中获取Dimension并返回
     */
    public static int getDimen(@DimenRes int id)
    {
        return (int) getDimenFloat(id);
    }

    /**
     * 从资源中获取Dimension并返回float类型
     */
    public static float getDimenFloat(@DimenRes int id)
    {
        return Easy.getContext().getResources().getDimension(id);
    }

    /**
     * 从资源中获取Dimension并返回
     */
    public static int getDimenToDp(@DimenRes int id)
    {
        return (int) getDimenFloat(id);
    }

    /**
     * 从资源中获取Dimension并返回float类型
     */
    public static float getDimenFloatToDp(@DimenRes int id)
    {
        return DensityTool.px2dp(Easy.getContext().getResources().getDimension(id));
    }

    /**
     * 从资源中获取Color
     *
     * @param id
     * @return
     */
    public static int getColor(@ColorRes int id)
    {
        return ContextCompat.getColor(Easy.getContext(), id);
    }

    /**
     * 获取 Configuration
     */
    public static Configuration getConfiguration()
    {
        return Easy.getContext().getResources().getConfiguration();
    }
}
