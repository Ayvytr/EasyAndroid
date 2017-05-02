package com.ayvytr.easyandroid.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * 继承 {@link BaseActivity} 的抽象类，onCreate中添加了 {@link #init(Bundle)} 方法，其中包含
 * {@link #initExtra()}, {@link #initView(Bundle)}, {@link #initData()}, 分别获取Bundle中的数据，
 * 初始化View(如果要使用BufferKnife，可以在这个方法绑定)，初始化数据，Activity的content view通过方法
 * {@link #getContentLayoutRes()} 获取.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.8
 */

public abstract class BaseEasyActivity extends BaseActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutRes());

        init(savedInstanceState);
    }

    /**
     * 返回Layout resource id，需要在子类实现.
     *
     * @return Layout resource id
     */
    @LayoutRes
    protected abstract int getContentLayoutRes();

    protected void init(Bundle savedInstanceState)
    {
        initExtra();
        initView(savedInstanceState);
        initData();
    }

    protected void initData()
    {

    }

    protected abstract void initView(Bundle savedInstanceState);

    protected void initExtra()
    {

    }

}
