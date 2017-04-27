package com.ayvytr.easyandroid.tools.withcontext;


import com.ayvytr.easyandroid.exception.UnsupportedInitializationException;

/**
 * 继承 {@link ResTool}, 简化ResTool类名为Res，调用更方便.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.8
 */

public class Res extends ResTool
{
    Res()
    {
        throw new UnsupportedInitializationException();
    }
}
