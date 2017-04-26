package com.ayvytr.easyandroid.exception;

/**
 * Desc:
 * Date: 2017/4/26
 *
 * @author davidwang
 */

public class ClipboardUnsupportedException extends RuntimeException
{
    public ClipboardUnsupportedException()
    {
        super("ClipboardManager can not be used when SDK_INT < 11\n" +
                "剪贴板不能在SDK_IND < 11 的版本上使用.");
    }
}
