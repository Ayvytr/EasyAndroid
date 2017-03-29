package com.ayvytr.logger;

/**
 * Created by davidwang on 2017/3/15.
 * <p>
 * 不支持构造函数初始化操作。在不需要创建类实例的类的私有无参构造中使用
 */

public class UnsupportedInitializationException extends RuntimeException
{
    public UnsupportedInitializationException()
    {
        super(UnsupportedInitializationException.class.getSimpleName() +
                ".You can't initialize this class. 你不能初始化这个类");
    }
}
