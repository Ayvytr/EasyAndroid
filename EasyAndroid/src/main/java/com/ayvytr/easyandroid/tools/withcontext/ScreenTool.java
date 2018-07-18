package com.ayvytr.easyandroid.tools.withcontext;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static com.ayvytr.easyandroid.tools.withcontext.ResTool.getConfiguration;

/**
 * 提供了获取屏幕尺寸，宽高，屏幕旋转方向，设置为竖屏，是不是横屏/竖屏，获取屏幕截图（包含/不包含状态栏)，
 * 判断是不是锁屏的功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */
public class ScreenTool
{
    private ScreenTool()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取屏幕DisplayMetrics
     *
     * @return DisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics()
    {
        WindowManager windowManager = Managers.getWindowManager();
        // 创建了一张白纸
        DisplayMetrics dm = new DisplayMetrics();
        // 给白纸设置宽高
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getPoint()
    {
        Point point = new Point();
        Managers.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth()
    {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight()
    {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return 屏幕宽度
     */
    public static int getWidth()
    {
        return getScreenWidth();
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高度
     */
    public static int getHeight()
    {
        return getScreenHeight();
    }

    /**
     * 设置屏幕为横屏
     * <p>还有一种就是在Activity中加属性android:screenOrientation="landscape"</p>
     * <p>不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次</p>
     * <p>设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次</p>
     * <p>设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"（4.0以上必须带最后一个参数）时
     * 切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法</p>
     *
     * @param activity activity
     */
    public static void setLandscape(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 设置屏幕为竖屏
     *
     * @param activity activity
     */
    public static void setPortrait(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 判断是否横屏
     *
     * @return {@code true} 是横屏<br>
     */
    public static boolean isLandscape()
    {
        Configuration configuration = getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 判断是否竖屏
     *
     * @return {@code true} 是竖屏<br>
     */
    public static boolean isPortrait()
    {
        Configuration configuration = getConfiguration();
        return configuration.orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 获取屏幕旋转角度
     *
     * @param activity activity
     * @return 屏幕旋转角度
     */
    public static int getScreenRotationAngle(Activity activity)
    {
        switch(activity.getWindowManager().getDefaultDisplay().getRotation())
        {
            default:
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
    }

    /**
     * 获取屏幕旋转角度
     *
     * @param activity activity
     * @return 屏幕旋转角度
     */
    public static int getRotationAngle(Activity activity)
    {
        switch(activity.getWindowManager().getDefaultDisplay().getRotation())
        {
            default:
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap captureWithStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap ret = Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels);
        view.destroyDrawingCache();
        return ret;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap captureWithoutStatusBar(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int statusBarHeight = BarTool.getStatusBarHeight(activity);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap ret = Bitmap.createBitmap(bmp, 0, statusBarHeight, dm.widthPixels,
                dm.heightPixels - statusBarHeight);
        view.destroyDrawingCache();
        return ret;
    }

    /**
     * 判断是否锁屏
     *
     * @return {@code true} 是锁屏
     */
    public static boolean isScreenLock()
    {
        return Managers.getKeyguardManager().inKeyguardRestrictedInputMode();
    }

    /**
     * 切换Activity到全屏状态
     *
     * @param activity     AppCompatActivity
     * @param toFullScreen {@code true} 切换到全屏 {@code false} 退出全屏
     */
    public static void switchFullScreen(Activity activity, boolean toFullScreen)
    {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attrs = window.getAttributes();
        if(toFullScreen)
        {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }
        else
        {
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }
        window.setAttributes(attrs);
    }

    /**
     * 切换Activity到全屏状态，同时显示或者隐藏ActionBar，如果是AppCompatActivity，切换到全屏，如果
     * ActionBar没隐藏，请检查是否调用过 {@link AppCompatActivity#setSupportActionBar };
     *
     * @param activity     AppCompatActivity
     * @param toFullScreen {@code true} 切换到全屏 {@code false} 退出全屏
     */
    public static void switchFullScreenWithActionBar(Activity activity, boolean toFullScreen)
    {
        switchFullScreen(activity, toFullScreen);
        showActionBar(activity, toFullScreen);
    }

    /**
     * 显示或隐藏ActionBar
     *
     * @param activity 目标Activity
     * @param isShow   {@code true} 显示ActionBar {@code false} 隐藏ActionBar
     */
    private static void showActionBar(Activity activity, boolean isShow)
    {
        if(activity instanceof AppCompatActivity)
        {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            try
            {
                if(isShow)
                {
                    actionBar.hide();
                }
                else
                {
                    actionBar.show();
                }
            } catch(NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        else if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
        {
            android.app.ActionBar actionBar = activity.getActionBar();
            try
            {
                if(isShow)
                {
                    actionBar.hide();
                }
                else
                {
                    actionBar.show();
                }
            } catch(NullPointerException e)
            {
                e.printStackTrace();
            }
        }
    }
}