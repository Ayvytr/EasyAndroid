package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.Convert;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author davidwang
 * @date 2017/3/16
 */

public class ConvertTest
{
    @Test
    public void testDoubleEquals()
    {
        double d = 1.1;
        double d1 = new Double(1.1);

        d = 0;
        d1 = 0 / 1.1;

        assertEquals(d == d1, Convert.toBool(d));

        d = 0.00001;

    }

}
