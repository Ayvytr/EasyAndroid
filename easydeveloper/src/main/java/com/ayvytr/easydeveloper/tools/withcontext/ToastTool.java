package com.ayvytr.easydeveloper.tools.withcontext;

import android.widget.Toast;

import com.ayvytr.easydeveloper.Easy;
import com.ayvytr.easydeveloper.exception.UnsupportedInitializationException;

/**
 * Created by davidwang on 2017/3/15.
 *
 * Toast工具，简便Toast创建和输出
 */

public class ToastTool
{
    private ToastTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 创建Toast并返回
     * @param text Toast文本
     * @return Toast
     */
    public static Toast make(String text)
    {
        return Toast.makeText(Easy.getContext(), text, Toast.LENGTH_SHORT);
    }

    /**
     * 创建LENGTH_LONG Toast并返回
     * @param text Toast文本
     * @return Toast
     */
    public static Toast makeLong(String text)
    {
        return Toast.makeText(Easy.getContext(), text, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast
     * @param text Toast文本
     */
    public static void show(String text)
    {
        make(text).show();
    }

    /**
     * 显示LENGTH_LONG Toast
     * @param text Toast文本
     */
    public static void showLong(String text)
    {
        makeLong(text).show();
    }
}
