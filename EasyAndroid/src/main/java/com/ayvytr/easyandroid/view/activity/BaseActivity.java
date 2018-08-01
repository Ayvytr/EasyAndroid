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
 * 继承 {@link AppCompatActivity} 的子类，实现了基本的 {@link #getContext()}, {@link #getActivity()}
 * 方法，便于在其实现类中使用.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.8.0
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 获取返回Context（还是此Activity)，讨厌每次在内部类写 *Activity.this.
     *
     * @return Context
     */
    public Context getContext() {
        return this;
    }

    /**
     * 获取返回Activity.
     *
     * @return Activity
     */
    public Activity getActivity() {
        return this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());

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
    protected abstract int getContentViewRes();

    /**
     * 初始化数据
     */
    protected void initData() {

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
    protected void initExtra() {

    }

    public void disableFontScaleWithSystem(boolean enabled)
    {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //非默认值
        if(newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        //非默认值
        if(res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            //设置默认
            newConfig.setToDefaults();
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
