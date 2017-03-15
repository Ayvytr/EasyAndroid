package com.ayvytr.easydeveloper.tools.withcontext;

import com.ayvytr.easydeveloper.Easy;
import com.ayvytr.easydeveloper.exception.UnsupportedInitializationException;

/**
 * Created by davidwang on 2017/3/15.
 * DensityTool
 *      dp - px 相互转化。提供int，float，double重载，尽可能最大化方便计算，减少类外强转
 */

public class DensityTool
{
    private DensityTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 将px转换为dp
     */
    public static int px2dp(int px)
    {
        return (int) px2dp((float)px);
    }

    /**
     * 将px转换为dp，接收/返回float
     */
    public static float px2dp(float px)
    {
        return (float) px2dp((double)px);
    }

    /**
     * 将px转换为dp，接收/返回double
     */
    public static double px2dp(double px)
    {
        float scale = Easy.getContext().getResources().getDisplayMetrics().density;
        return px / scale + 0.5;
    }

    /**
     * 将dp转换为px，接收/返回int
     */
    public static int dp2px(int dp)
    {
        return (int) dp2px((float)dp);
    }

    /**
     * 将dp转换为px，接收/返回float
     */
    public static float dp2px(float dp)
    {
        return (float) dp2px((double)dp);
    }

    /**
     * 将dp转换为px，接收/返回double
     */
    public static double dp2px(double dp)
    {
        float scale = Easy.getContext().getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
