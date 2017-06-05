package com.ayvytr.easyandroid;

import com.ayvytr.easyandroid.tools.RegexTool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Do on 2017/6/2.
 */

public class RegexToolTest
{
    @Test
    public void testTel()
    {
        String tel = null;
        assertEquals(false, RegexTool.isTel(tel));

        tel = "";
        assertEquals(false, RegexTool.isTel(tel));

        tel = "00000";
        assertEquals(false, RegexTool.isTel(tel));

        tel = "8888888";
        assertEquals(false, RegexTool.isTel(tel));

        tel = "100-11111111";
        assertEquals(false, RegexTool.isTel(tel));

        tel = "000-1111111";
        assertEquals(true, RegexTool.isTel(tel));

        tel = "011-0000000";
        assertEquals(true, RegexTool.isTel(tel));

        tel = "0937-0000000";
        assertEquals(true, RegexTool.isTel(tel));

        tel = "0999-88888888";
        assertEquals(true, RegexTool.isTel(tel));
    }

    @Test
    public void testMobile()
    {
        String tel = null;
        assertEquals(false, RegexTool.isMobile(tel));

        tel = "";
        assertEquals(false, RegexTool.isMobile(tel));

        tel = "00000";
        assertEquals(false, RegexTool.isMobile(tel));

        tel = "8888888";
        assertEquals(false, RegexTool.isMobile(tel));

        tel = "18500000000";
        assertEquals(true, RegexTool.isMobile(tel));

        tel = "15000000000";
        assertEquals(true, RegexTool.isMobile(tel));

        tel = "13400000000";
        assertEquals(true, RegexTool.isMobile(tel));

        tel = "13500000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "13600000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "15000000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "15100000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "15700000000";
        assertEquals(true, RegexTool.isMobile(tel));

        tel = "14000000000";
        assertEquals(false, RegexTool.isMobile(tel));
        tel = "14500000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "14700000000";
        assertEquals(true, RegexTool.isMobile(tel));

        tel = "14800000000";
        assertEquals(false, RegexTool.isMobile(tel));

        tel = "13000000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "13100000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "13200000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "15200000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "15500000000";

        assertEquals(true, RegexTool.isMobile(tel));
        tel = "18500000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "18600000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "13300000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "15300000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "18000000000";
        assertEquals(true, RegexTool.isMobile(tel));
        tel = "18900000000";
        assertEquals(true, RegexTool.isMobile(tel));
    }
}
