package com.ayvytr.easyandroid.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ayvytr.easyandroid.Easy;
import com.ayvytr.easyandroid.exception.UnsupportedInitializationException;

/**
 * View布局操作类，包括设置可见/隐藏，获取/取消焦点，调用 {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
 * 加载布局，以及最重要的 {@link #inflate2(int, ViewGroup)}，专门为RecyclerView.onCreateViewHolder准备.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.8.1.2
 */

public class ViewTool
{
    private ViewTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 显示view
     *
     * @param view 目标View
     */
    public static void show(View view)
    {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏view
     *
     * @param view 目标View
     */
    public static void hide(View view)
    {
        view.setVisibility(View.GONE);
    }

    /**
     * 设置view为invisible
     *
     * @param view 目标View
     */
    public static void invisible(View view)
    {
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 获取view焦点
     *
     * @param view 目标View
     */
    public static void requestFocus(View view)
    {
        view.requestFocus();
    }

    /**
     * 取消view焦点
     *
     * @param view 目标View
     */
    public static void clearFocus(View view)
    {
        view.clearFocus();
    }

    /**
     * 加载布局并返回，详情请看{@link LayoutInflater#inflate(int, ViewGroup)}.
     *
     * @param id 布局id
     * @return 加载的布局
     */
    public static View inflate(@LayoutRes int id)
    {
        return inflate(id, null);
    }

    /**
     * 加载布局并返回，详情请看{@link LayoutInflater#inflate(int, ViewGroup)}.
     *
     * @param id     布局id
     * @param parent 父布局
     * @return 加载的布局
     */
    public static View inflate(@LayoutRes int id, ViewGroup parent)
    {
        return inflate(id, parent, parent != null);
    }

    /**
     * 加载布局并返回，详情请看{@link LayoutInflater#inflate(int, ViewGroup, boolean)}.
     *
     * @param id             布局id
     * @param parent         父布局
     * @param attachToParent 是否给加载的布局文件指定父布局
     * @return 加载的布局
     */
    public static View inflate(@LayoutRes int id, ViewGroup parent, boolean attachToParent)
    {
        return LayoutInflater.from(Easy.getContext()).inflate(id, parent, attachToParent);
    }

    /**
     * 加载布局并返回，专为RecyclerView提供，不给布局文件指定父布局，
     * 详情请看{@link LayoutInflater#inflate(int, ViewGroup, boolean)}.
     *
     * @param id     布局id
     * @param parent 父布局
     * @return 加载的布局
     */
    public static View inflate2(@LayoutRes int id, ViewGroup parent)
    {
        return inflate(id, parent, false);
    }

    /**
     * 设置 {@link EditText} 文本，并且移动光标到末尾.
     *
     * @param et   {@link EditText}
     * @param text 文本
     */
    public void setEtText(EditText et, CharSequence text)
    {
        et.setText(text);
        et.setSelection(text.length());
    }

    /**
     * 设置 {@link EditText} 文本，并且选择文本.
     *
     * @param et    {@link EditText}
     * @param text  文本
     * @param start 光标开始位置
     * @param stop  光标结束位置
     */
    public void setEtText(EditText et, CharSequence text, int start, int stop)
    {
        et.setText(text);
        et.setSelection(start, stop);
    }

    /**
     * 设置 {@link EditText} 文本，并且移动光标到指定位置.
     *
     * @param et    {@link EditText}
     * @param text  文本
     * @param index 光标位置
     */
    public void setEtText(EditText et, CharSequence text, int index)
    {
        et.setText(text);
        et.setSelection(index);
    }
}
