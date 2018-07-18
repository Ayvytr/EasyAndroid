package com.ayvytr.easyandroid;

import android.content.Context;


/**
 * 这个库的单例入口类, 使用有关Context的类之前，需要初始化这个类.
 * <p>
 * 提供了获取Context，常用SystemService等方法，在使用 ClipboardTool, DensityTool 等类之前，
 * 需要调用'Easy.getDefault().initTag(context);' 初始化。
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class Easy
{
    private static Easy easy = new Easy();
    private static Context context;

    /**
     * 返回 {@link Context}
     *
     * @return {@link Context}
     */
    public static Context getContext()
    {
        checkInitState();
        return context;
    }

    /**
     * 检测初始化状态。Easy类暂时定为静态初始化，所以直接检测contextRef是否已初始化，
     * 未初始化，直接抛出异常.
     */
    private static void checkInitState()
    {
        if(context == null)
        {
            throw new IllegalStateException("未初始化库异常，在使用之前，你需要在'Application.onCreate()'" +
                    "(或者其他位置)中调用'Easy.getDefault().initTag(Context)初始化。");
        }
    }

    private Easy()
    {
    }

    /**
     * 单例初始化 {@link Easy} 方法，并返回 {@link Easy} 对象
     *
     * @return {@link Easy} 对象
     */
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
            throw new NullPointerException("Argument Context is null(参数Context是null).");
        }

        Easy.context = context;
    }

    /**
     * 释放资源（可能用不到，但还是提供吧)
     */
    public void release()
    {
        context = null;
        easy = null;
    }

}
