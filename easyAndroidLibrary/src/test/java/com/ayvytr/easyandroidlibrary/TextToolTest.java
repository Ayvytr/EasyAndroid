package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.TextTool;

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
    public void isBlock()
    {
        String str = null;
        assertEquals(TextTool.isBlank(str), true);
        str = "";
        assertEquals(TextTool.isBlank(str), true);
        str = "            ";
    }
}
