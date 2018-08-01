package com.ayvytr.easyandroid.view.activity;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 继承 {@link AppCompatActivity} 的子类，实现了 {@link #getContext()}, {@link #getActivity()}
 * 等方法，便于在其实现类中使用.
 * <p>
 * 从2.2.0版本开始，删除BaseEasyActivity类，尽可能减少继承层级.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.8.0
 */

public abstract class BaseActivity extends AppCompatActivity
{
    /**
     * 是否禁用Activity中sp为单位的字体随系统缩放而缩放。 true: 禁用字体随系统缩放; false:启用字体随系统缩放
     */
    private boolean isFontScaleDisabled = true;

    /**
     * 获取返回Context（还是此Activity)，讨厌每次在内部类写 *Activity.this.
     *
     * @return Context
     */
    public Context getContext()
    {
        return this;
    }

    /**
     * 获取返回Activity.
     *
     * @return Activity
     */
    public Activity getActivity()
    {
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutRes());

        init(savedInstanceState);
    }

    protected void init(@Nullable Bundle savedInstanceState)
    {
        initExtra();
        initView(savedInstanceState);
        initData();
    }

    /**
     * 返回Layout resource id，需要在子类实现.
     *
     * @return Layout resource id
     */
    @LayoutRes
    protected abstract int getContentLayoutRes();

    /**
     * 初始化数据
     */
    protected void initData()
    {

    }

    /**
     * 初始化View
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化外部传入的Extra数据
     */
    protected void initExtra()
    {

    }

    /**
     * 是否禁用sp为单位的字体随系统缩放
     *
     * @param fontScaleDisabled 是否禁用sp为单位的字体随系统缩放
     * @see #isFontScaleDisabled
     */
    public void setFontScaleDisabled(boolean fontScaleDisabled)
    {
        isFontScaleDisabled = fontScaleDisabled;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        if(isFontScaleDisabled)
        {
            //非默认值
            if(newConfig.fontScale != 1)
            {
                useFontScaleDisabledResources();
            }
        }
        super.onConfigurationChanged(newConfig);
    }


    /**
     * 禁用字体随系统缩放功能
     * @return {@link Resources}
     */
    public Resources useFontScaleDisabledResources()
    {
        Resources res = super.getResources();
        //非默认值
        if(res.getConfiguration().fontScale != 1)
        {
            Configuration newConfig = new Configuration();
            //设置默认
            newConfig.setToDefaults();
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
