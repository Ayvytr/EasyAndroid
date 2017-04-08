package com.ayvytr.easyandroid.tools;

import android.text.TextUtils;

import com.ayvytr.easyandroid.exception.UnsupportedInitializationException;

/**
 * 提供了众多的字符串操作功能，包括判空，是不是字符串，分割字符串（会去掉末尾的regex）等功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class TextTool
{
    private TextTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 判断字符串是不是空值：str == null / str.length() == 0.
     *
     * @param str 字符串
     * @return {@code true} 是空字符串<br>
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是不是纯数字字符
     *
     * @param str 字符串
     * @return {@code true} 是纯数字字符串
     */
    public static boolean isDigit(String str)
    {
        if(isEmpty(str))
        {
            return false;
        }

        return TextUtils.isDigitsOnly(str);
    }

    /**
     * 判断字符串是不是纯数字字符
     *
     * @param str 字符串
     * @return {@code true} 是纯数字字符串
     */
    public static boolean isNumber(String str)
    {
        return isDigit(str);
    }

    /**
     * 字符串反转，如果字符串为空，返回空字符串
     *
     * @param str 字符串
     * @return 反转后的字符串
     */
    public static String reverse(String str)
    {
        if(isEmpty(str))
        {
            return emptyString();
        }

        int length = str.length();
        if(length <= 1)
        {
            return str;
        }

        int mid = length >> 1;
        char[] chars = str.toCharArray();
        char c;
        for(int i = 0; i < mid; ++i)
        {
            c = chars[i];
            chars[i] = chars[length - i - 1];
            chars[length - i - 1] = c;
        }

        return new String(chars);
    }

    /**
     * 返回长度为0的空字符串.
     *
     * @return 空字符串
     */
    public static String emptyString()
    {
        return "";
    }

    /**
     * 字符串分割方法，当字符串末尾是 regex 时，循环去掉 regex，以处理后的字符串进行分割
     *
     * @param str   要分割的字符串
     * @param regex 分割
     * @return 分割后的字符串
     */
    public static String[] split(String str, String regex)
    {
        if(regex == null)
        {
            throw new NullPointerException();
        }

        String s = str;
        while(s.endsWith(regex))
        {
            s = s.substring(0, s.lastIndexOf(regex));
        }

        return s.split(regex);
    }

    /**
     * 判断字符串是不是空白字符串, null/长度为0/全部为空格
     *
     * @param str 字符串
     * @return {@code true} 是空白字符串
     */
    public static boolean isBlank(String str)
    {
        if(isEmpty(str))
        {
            return true;
        }

        return isEmpty(str.trim());
    }
}
