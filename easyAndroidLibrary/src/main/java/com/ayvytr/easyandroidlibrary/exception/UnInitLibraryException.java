package com.ayvytr.easyandroidlibrary.exception;

/**
 * 使用withcontext包中的类时，未调用 Easy类的初始化过程抛出此异常.
 */

public class UnInitLibraryException extends RuntimeException
{
    public UnInitLibraryException()
    {
        super(UnInitLibraryException.class.getSimpleName() +
                ".Before you use, " +
                "you need call 'Easy.getDefault.init(Context)' " +
                "on your method 'Application.onCreate()'" +
                "(or any other location) to init.\n" +
                "未初始化库异常，在使用之前，你需要在'Application.onCreate()'" +
                "(或者其他位置)中调用'Easy.getDefault().init(Context)初始化。");
    }
}
