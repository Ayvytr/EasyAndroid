package com.ayvytr.easyandroidlibrary.tools.withcontext;

import android.widget.Toast;

import com.ayvytr.easyandroidlibrary.Easy;
import com.ayvytr.easyandroidlibrary.exception.UnsupportedInitializationException;

/**
 * Toast工具，提供简便的Toast创建和输出功能.
 * @see Toast
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class ToastTool
{
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
        return Toast.makeText(Easy.getContext(), text, Toast.LENGTH_SHORT);
    }

    /**
     * 创建LENGTH_LONG Toast并返回
     *
     * @param text Toast文本
     * @return Toast
     */
    public static Toast makeLong(String text)
    {
        return Toast.makeText(Easy.getContext(), text, Toast.LENGTH_LONG);
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
}
