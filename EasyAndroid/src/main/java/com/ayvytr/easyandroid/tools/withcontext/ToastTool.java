package com.ayvytr.easyandroid.tools.withcontext;

import android.annotation.SuppressLint;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.ayvytr.easyandroid.Easy;
import com.ayvytr.easyandroid.exception.UnsupportedInitializationException;

/**
 * Toast工具类，提供简便的Toast创建和输出功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @see Toast
 * @since 1.0.0
 */

public class ToastTool
{
    /**
     * 改成1个Toast实例接受每次更改和显示。Toast显示时，多次点击同一个Toast，Toast不会重复显示；切换显示其他
     * Toast时，有动态切换的效果，很赞，推荐使用
     */
    private static Toast toast;

    private ToastTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 创建Toast并返回
     *
     * @param text Toast文本
     * @return Toast
     */
    public static Toast make(String text)
    {
        return create(text, Toast.LENGTH_SHORT);
    }

    /**
     * 创建并返回Toast.
     */
    @SuppressLint("ShowToast")
    private static Toast create(String text, int length)
    {
        if(toast == null)
        {
            toast = Toast.makeText(Easy.getContext(), text, length);
        }
        else
        {
            toast.setDuration(length);
            toast.setText(text);
        }

        return toast;
    }

    /**
     * 创建LENGTH_LONG Toast并返回
     *
     * @param text Toast文本
     * @return Toast
     */
    public static Toast makeLong(String text)
    {
        return create(text, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     *
     * @param text Toast文本
     */
    public static void show(String text)
    {
        make(text).show();
    }

    /**
     * 显示LENGTH_LONG Toast
     *
     * @param text Toast文本
     */
    public static void showLong(String text)
    {
        makeLong(text).show();
    }

    /**
     * 创建Toast并返回
     *
     * @param id string id
     * @return Toast
     */
    public static Toast make(@StringRes int id)
    {
        return create(id, Toast.LENGTH_SHORT);
    }

    /**
     * 创建并返回Toast.
     */
    @SuppressLint("ShowToast")
    private static Toast create(@StringRes int id, int length)
    {
        if(toast == null)
        {
            toast = Toast.makeText(Easy.getContext(), id, length);
        }
        else
        {
            toast.setDuration(length);
            toast.setText(id);
        }

        return toast;
    }

    /**
     * 创建LENGTH_LONG Toast并返回
     *
     * @param id string id
     * @return Toast
     */
    public static Toast makeLong(@StringRes int id)
    {
        return create(id, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     *
     * @param id string id
     */
    public static void show(@StringRes int id)
    {
        make(id).show();
    }

    /**
     * 显示LENGTH_LONG Toast
     *
     * @param id string id
     */
    public static void showLong(@StringRes int id)
    {
        makeLong(id).show();
    }
}
