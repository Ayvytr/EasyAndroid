package com.ayvytr.easyandroid.view.activity;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * 继承 {@link AppCompatActivity} 的子类，实现了基本的 {@link #getContext()}, {@link #getActivity()}
 * 方法，便于在其实现类中使用.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.8.0
 */

public class BaseActivity extends AppCompatActivity
{
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
}
