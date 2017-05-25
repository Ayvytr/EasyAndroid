package com.ayvytr.easyandroid;

import com.ayvytr.easyandroid.tools.TextTool;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by davidwang on 2017/3/15.
 * 测试 TextTool类
 *
 * @see TextTool
 */

public class TextToolTest
{
    @Test
    public void isEmpty()
    {
        String str = null;
        assertEquals(TextTool.isEmpty(str), true);
        str = "";
        assertEquals(TextTool.isEmpty(str), true);
        str = "aaa";
        assertEquals(TextTool.isEmpty(str), false);
    }

    @Test
    public void reverse()
    {
        String src = "abcdefg";
        String reverse = TextTool.reverse(src);
        assertEquals(src, TextTool.reverse(reverse));

        src = "中国人";
        reverse = TextTool.reverse(src);
        assertEquals(src, TextTool.reverse(reverse));

        src = "支A持b国C货";
        reverse = TextTool.reverse(src);
        assertEquals(src, TextTool.reverse(reverse));
    }

    @Test
    public void regex()
    {
        String src = "a;b;c;;;";
        String[] split = TextTool.split(src, ";");
        String[] values = new String[]
                {
                        "a",
                        "b",
                        "c"
                };
        assertArrayEquals(split, values);

        src = "ab::cdddd::aaaaa::;:::::::::";
        split = TextTool.split(src, "::");
        values = new String[]
                {
                        "ab",
                        "cdddd",
                        "aaaaa",
                        ";:"
                };
        assertArrayEquals(split, values);
    }

    @Test
    public void isBlank()
    {
        String str = null;
        assertEquals(TextTool.isBlank(str), true);
        str = "";
        assertEquals(TextTool.isBlank(str), true);
        str = "            ";
        assertEquals(TextTool.isBlank(str), true);
    }

    @Test
    public void equals()
    {
        CharSequence str = null;
        CharSequence str1 = null;
        boolean equals = TextTool.equals(str, str1);
        assertEquals(equals, true);
        str = "";
        equals = TextTool.equals(str, str1);
        assertEquals(equals, false);
        str = null;
        str1 = "";
        equals = TextTool.equals(str, str1);
        assertEquals(equals, false);

        str = "1";
        str1 = "1";
        equals = TextTool.equals(str1, str);
        assertEquals(equals, true);

        str = "1";
        str1 = str;
        equals = TextTool.equals(str, str1);
        assertEquals(equals, true);
    }

    @Test
    public void equalsIgnoreCase()
    {
        CharSequence str = null;
        CharSequence str1 = null;
        boolean equals = TextTool.equalsIgnoreCase(str, str1);
        assertEquals(equals, true);
        str = "";
        equals = TextTool.equalsIgnoreCase(str, str1);
        assertEquals(equals, false);
        str = null;
        str1 = "";
        equals = TextTool.equalsIgnoreCase(str, str1);
        assertEquals(equals, false);

        str = "1";
        str1 = "1";
        equals = TextTool.equalsIgnoreCase(str1, str);
        assertEquals(equals, true);

        str = "1";
        str1 = str;
        equals = TextTool.equalsIgnoreCase(str, str1);
        assertEquals(equals, true);

        str1 = "AAAAbbb";
        str = "aaaaBBB";
        equals = TextTool.equalsIgnoreCase(str, str1);
        assertEquals(equals, true);
    }
}
