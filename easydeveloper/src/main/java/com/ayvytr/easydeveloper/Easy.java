package com.ayvytr.easydeveloper;

import android.app.KeyguardManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.WindowManager;

import com.ayvytr.easydeveloper.exception.UnInitLibraryException;

import java.lang.ref.SoftReference;

/**
 * Created by davidwang on 2017/3/15.
 * Easy:入口类，单例模式。Tools包中或者其他需要用到Context的类在使用之前，
 * 需要初始化这个类
 *
 * 提供了获取Context，常用SystemService等方法
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

    /**
     * 获取 ClipboardManager
     */
    public ClipboardManager getClipboardManager()
    {
        return (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * 获取WindowManager
     */
    public WindowManager getWindowManager()
    {
        return (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 获取KeyguardManager
     */
    public KeyguardManager getKeyguardManager()
    {
        return (KeyguardManager) getContext().getSystemService(Context.KEYGUARD_SERVICE);
    }
}
