package com.ayvytr.easyandroid.tools.withcontext;

import android.content.Context;

import com.ayvytr.easyandroid.Easy;

/**
 * Dp - Px 相互转化，提供了int，float，double 3种类型的重载方法，尽可能减少外部强制类型转换，
 * 并添加了Context参数，直接通过Context进行转换.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class DensityTool
{
    private DensityTool()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * 将px转换为dp
     *
     * @param px 像素值
     * @return dp值
     */
    public static int px2dp(int px)
    {
        return (int) px2dp((float) px);
    }

    /**
     * 将px转换为dp，接收/返回float
     *
     * @param px 像素值
     * @return dp值
     */
    public static float px2dp(float px)
    {
        return (float) px2dp((double) px);
    }

    /**
     * 将px转换为dp，接收/返回double
     *
     * @param px 像素值
     * @return dp值
     */
    public static double px2dp(double px)
    {
        float scale = Easy.getContext().getResources().getDisplayMetrics().density;
        return px / scale + 0.5;
    }

    /**
     * 将dp转换为px，接收/返回int
     *
     * @param dp dp值
     * @return 像素值
     */
    public static int dp2px(int dp)
    {
        return (int) dp2px((float) dp);
    }

    /**
     * 将dp转换为px，接收/返回float
     *
     * @param dp dp值
     * @return 像素值
     */
    public static float dp2px(float dp)
    {
        return (float) dp2px((double) dp);
    }

    /**
     * 将dp转换为px，接收/返回double
     *
     * @param dp dp值
     * @return 像素值
     */
    public static double dp2px(double dp)
    {
        float scale = Easy.getContext().getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    /**
     * 将px转换为dp
     *
     * @param context Context
     * @param px      像素值
     * @return dp值
     */
    public static int px2dp(Context context, int px)
    {
        return (int) px2dp(context, (float) px);
    }

    /**
     * 将px转换为dp，接收/返回float
     *
     * @param context Context
     * @param px      像素值
     * @return dp值
     */
    public static float px2dp(Context context, float px)
    {
        return (float) px2dp(context, (double) px);
    }

    /**
     * 将px转换为dp，接收/返回double
     *
     * @param context Context
     * @param px      像素值
     * @return dp值
     */
    public static double px2dp(Context context, double px)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return px / scale + 0.5;
    }

    /**
     * 将dp转换为px，接收/返回int
     *
     * @param context Context
     * @param dp      dp值
     * @return 像素值
     */
    public static int dp2px(Context context, int dp)
    {
        return (int) dp2px(context, (float) dp);
    }

    /**
     * 将dp转换为px，接收/返回float
     *
     * @param context Context
     * @param dp      dp值
     * @return 像素值
     */
    public static float dp2px(Context context, float dp)
    {
        return (float) dp2px(context, (double) dp);
    }

    /**
     * 将dp转换为px，接收/返回double
     *
     * @param context Context
     * @param dp      dp值
     * @return 像素值
     */
    public static double dp2px(Context context, double dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
