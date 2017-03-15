package com.ayvytr.easydeveloper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ayvytr.easydeveloper.tools.withcontext.DensityTool;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 * 测试 DensityTool 的工具类，对比类：DensityUtils
 * @see DensityTool
 * @see DensityUtils
 */
@RunWith(AndroidJUnit4.class)
public class DensityToolTest
{
    @Before
    public void init()
    {
        Context context = InstrumentationRegistry.getTargetContext();
        Easy.getDefault().init(context);
    }

    @Test
    public void dp2px() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();

        for(int i = 0; i < 100; i++)
        {
            assertEquals(DensityUtils.dp2px(context, i), DensityTool.dp2px(i));
        }
    }

    @Test
    public void dp2pxFloat() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();

        float seed = 0.3f;
        float value = seed;

        for(int i = 0; i < 100; i++, value += i + seed)
        {
            assertEquals(DensityUtils.dp2px(context, value), DensityTool.dp2px(value), 0.001f);
        }

    }

    @Test
    public void dp2pxDouble() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();

        double seed = 0.3f;
        double value = seed;

        for(int i = 0; i < 100; i++, value += i + seed)
        {
            assertEquals(DensityUtils.dp2px(context, value), DensityTool.dp2px(value), 0.001f);
        }

    }

    @Test
    public void px2dp() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();

        for(int i = 0; i < 100; i++)
        {
            assertEquals(DensityUtils.px2dp(context, i), DensityTool.px2dp(i));
        }
    }

    @Test
    public void px2dpFloat() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();

        float seed = 0.3f;
        float value = seed;

        for(int i = 0; i < 100; i++, value += i + seed)
        {
            assertEquals(DensityUtils.px2dp(context, value), DensityTool.px2dp(value), 0.001f);
        }

    }

    @Test
    public void px2dpDouble() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();

        double seed = 0.3f;
        double value = seed;

        for(int i = 0; i < 100; i++, value += i + seed)
        {
            assertEquals(DensityUtils.px2dp(context, value), DensityTool.px2dp(value), 0.001f);
        }

    }

    /**
     * dip与px相互转换的工具类
     *
     * @author shkstart
     */
    static class DensityUtils
    {
        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static float dp2px(Context context, float dpValue)
        {
            final float density =
                    context.getResources().getDisplayMetrics().density;
            return dpValue * density + 0.5f;
        }

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static int dp2px(Context context, int dpValue)
        {
            final float density =
                    context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * density + 0.5f);
        }

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static double dp2px(Context context, double dpValue)
        {
            final float density =
                    context.getResources().getDisplayMetrics().density;
            return dpValue * density + 0.5f;
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        public static int px2dp(Context context, int pxValue)
        {
            final float density =
                    context.getResources().getDisplayMetrics().density;
            return (int) ((float)pxValue / density + 0.5f);
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        public static float px2dp(Context context, float pxValue)
        {
            final float density =
                    context.getResources().getDisplayMetrics().density;
            return pxValue / density + 0.5f;
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        public static double px2dp(Context context, double pxValue)
        {
            final float density =
                    context.getResources().getDisplayMetrics().density;
            return pxValue / density + 0.5f;
        }
    }
}
