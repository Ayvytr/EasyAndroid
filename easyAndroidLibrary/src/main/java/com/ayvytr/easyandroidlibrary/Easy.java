package com.ayvytr.easyandroidlibrary;

import android.content.Context;

import com.ayvytr.easyandroidlibrary.exception.UnInitLibraryException;

import java.lang.ref.SoftReference;

/**
 * 这个库的单例入口类, 使用有关Context的类之前，需要初始化这个类.
 * <p>
 * 提供了获取Context，常用SystemService等方法，在使用 ClipboardTool, DensityTool 等类之前，
 * 需要调用'Easy.getDefault().init(context);' 初始化。
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class Easy
{
    private static Easy easy = new Easy();
    private static SoftReference<Context> contextRef;

    public static Context getContext()
    {
        checkInitState();
        return contextRef.get();
    }

    /**
     * 检测初始化状态。Easy类暂时定为静态初始化，所以直接检测contextRef是否已初始化，
     * 未初始化，直接抛出自定义异常
     */
    private static void checkInitState()
    {
        if(contextRef == null)
        {
            throw new UnInitLibraryException();
        }
    }

    private Easy()
    {
    }

    public static Easy getDefault()
    {
        return easy;
    }

    /**
     * 初始化Context
     *
     * @param context context
     */
    public void init(Context context)
    {
        if(context == null)
        {
            throw new NullPointerException("Context is null on 'init'.");
        }

        this.contextRef = new SoftReference<>(context);
    }

    /**
     * 释放资源（可能用不到，但还是提供吧)
     */
    public void release()
    {
        contextRef = null;
        easy = null;
    }

}
