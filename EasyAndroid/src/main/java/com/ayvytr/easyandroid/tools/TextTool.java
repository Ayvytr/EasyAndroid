package com.ayvytr.easyandroid.tools;

import android.text.TextUtils;

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
        throw new UnsupportedOperationException();
    }

    /**
     * 判断字符串是不是空值：str == null / str.length() == 0.
     *
     * @param str 字符串
     * @return {@code true} 是空字符串<br>
     */
    public static boolean isEmpty(CharSequence str)
    {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是不是空值.
     *
     * @param s 目标字符串
     * @return {@code true} 不是空字符串
     */
    public static boolean isNotEmpty(CharSequence s)
    {
        return !isEmpty(s);
    }

    /**
     * 判断字符串是不是空值.
     *
     * @param s 目标字符串
     * @return {@code true} 不是空字符串
     */
    public static boolean notEmpty(CharSequence s)
    {
        return !isEmpty(s);
    }

    /**
     * 判断字符串是不是纯数字字符
     *
     * @param str 字符串
     * @return {@code true} 是纯数字字符串
     */
    public static boolean isDigit(CharSequence str)
    {
        return !isEmpty(str) && TextUtils.isDigitsOnly(str);
    }

    /**
     * 判断字符串是不是纯数字字符
     *
     * @param str 字符串
     * @return {@code true} 是纯数字字符串
     */
    public static boolean isNumber(CharSequence str)
    {
        return isDigit(str);
    }

    /**
     * 字符串反转，如果字符串为空，返回空字符串
     *
     * @param s 字符串
     * @return 反转后的字符串
     */
    public static String reverse(CharSequence s)
    {
        if(isEmpty(s))
        {
            return emptyString();
        }

        int length = s.length();
        if(length <= 1)
        {
            return s.toString();
        }

        int mid = length >> 1;
        char[] chars = s.toString().toCharArray();
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
        if(str == null || regex == null)
        {
            return new String[0];
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
    public static boolean isBlank(CharSequence str)
    {
        return isEmpty(str) || isEmpty(str.toString().trim());
    }

    /**
     * 比较2个字符串是否相等.
     *
     * @param str  字符串1
     * @param str2 字符串2
     * @return {@code true }相等 {@code false }不相等
     */
    public static boolean equals(CharSequence str, CharSequence str2)
    {
        if(str == str2)
        {
            return true;
        }

        try
        {
            return str.toString().equals(str2.toString());
        } catch(NullPointerException e)
        {
        }

        return false;
    }

    /**
     * 比较2个字符串是否相等，忽略大小写.
     *
     * @param str  字符串1
     * @param str2 字符串2
     * @return {@code true }相等 {@code false }不相等
     */
    public static boolean equalsIgnoreCase(CharSequence str, CharSequence str2)
    {
        if(str == str2)
        {
            return true;
        }

        try
        {
            return str.toString().equalsIgnoreCase(str2.toString());
        } catch(NullPointerException e)
        {
        }

        return false;
    }

    /**
     * 比较2个字符串是否不相等.
     * <p>
     * {@link #equals(CharSequence, CharSequence)}
     *
     * @param str  字符串1
     * @param str2 字符串2
     * @return {@code true }相等 {@code false }不相等
     */
    public static boolean notEquals(CharSequence str, CharSequence str2)
    {
        return !equals(str, str2);
    }

    /**
     * 比较2个字符串是否不相等，忽略大小写.
     * <p>
     * {@link #equalsIgnoreCase(CharSequence, CharSequence)}
     *
     * @param str  字符串1
     * @param str2 字符串2
     * @return {@code true }相等 {@code false }不相等
     */
    public static boolean notEqualsIgnoreCase(CharSequence str, CharSequence str2)
    {
        return !equalsIgnoreCase(str, str2);
    }
}
