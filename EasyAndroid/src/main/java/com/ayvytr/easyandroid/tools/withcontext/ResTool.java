package com.ayvytr.easyandroid.tools.withcontext;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.ayvytr.easyandroid.Easy;
import com.ayvytr.easyandroid.exception.UnsupportedInitializationException;

/**
 * 获取资源中的Drawable，String，dimension，color, Configuration.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class ResTool
{
    ResTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 从资源中获取Drawable
     *
     * @param id Drawable资源id
     * @return Drawable
     */
    public static Drawable getDrawable(@DrawableRes int id)
    {
        //使用ContextCompat应是谷歌推荐的
        return ContextCompat.getDrawable(Easy.getContext(), id);
    }

    /**
     * 从资源中获取String
     *
     * @param id String资源id
     * @return String
     */
    public static String getString(@StringRes int id)
    {
        return Easy.getContext().getString(id);
    }

    /**
     * 从资源中获取String
     *
     * @param id   String id
     * @param args 格式化参数
     * @return String
     */
    public static String getString(@StringRes int id, Object... args)
    {
        return Easy.getContext().getString(id, args);
    }

    /**
     * 从资源中获取Dimension并返回
     *
     * @param id 尺寸资源id
     * @return 尺寸像素值
     */
    public static int getDimen(@DimenRes int id)
    {
        return (int) getDimenFloat(id);
    }

    /**
     * 从资源中获取Dimension并返回float类型
     *
     * @param id 尺寸资源id
     * @return 尺寸像素值
     */
    public static float getDimenFloat(@DimenRes int id)
    {
        return getResources().getDimension(id);
    }

    /**
     * 从资源中获取Dimension并返回
     *
     * @param id 像素资源id
     * @return 尺寸dp值
     */
    public static int getDimenToDp(@DimenRes int id)
    {
        return (int) getDimenFloat(id);
    }

    /**
     * 从资源中获取Dimension并返回float类型
     *
     * @param id 像素资源id
     * @return 尺寸dp值
     */
    public static float getDimenFloatToDp(@DimenRes int id)
    {
        return DensityTool.px2dp(Easy.getContext().getResources().getDimension(id));
    }

    /**
     * 从资源中获取Color
     *
     * @param id 颜色资源id
     * @return 颜色值
     */
    public static int getColor(@ColorRes int id)
    {
        return ContextCompat.getColor(Easy.getContext(), id);
    }

    /**
     * 获取 Configuration
     *
     * @return Configuration
     */
    public static Configuration getConfiguration()
    {
        return getResources().getConfiguration();
    }

    /**
     * 获取 Resources
     *
     * @return Resources
     */
    public static Resources getResources()
    {
        return Easy.getContext().getResources();
    }

    /**
     * 获取返回String array.
     *
     * @param id resource id
     * @return String array
     */
    public String[] getStringArray(@ArrayRes int id)
    {
        return getResources().getStringArray(id);
    }

    /**
     * 获取返回int array.
     *
     * @param id resource id
     * @return int array
     */
    public int[] getIntArray(@ArrayRes int id)
    {
        return getResources().getIntArray(id);
    }

    /**
     * 获取返回text id.
     *
     * @param id resource id
     * @return text array
     */
    public CharSequence[] getTextArray(@ArrayRes int id)
    {
        return getResources().getTextArray(id);
    }
}
