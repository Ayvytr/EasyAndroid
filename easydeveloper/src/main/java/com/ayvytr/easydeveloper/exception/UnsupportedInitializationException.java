package com.ayvytr.easydeveloper.exception;

/**
 * Created by davidwang on 2017/3/15.
 */

public class UnsupportedInitializationException extends RuntimeException
{
    public UnsupportedInitializationException()
    {
        super(UnsupportedInitializationException.class.getSimpleName() +
        ".You can't initialize this class. 你不能初始化这个类");
    }
}
