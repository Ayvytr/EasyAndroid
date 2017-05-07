package com.ayvytr.easyandroid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ayvytr.easyandroid.bean.AppInfo;
import com.ayvytr.easyandroid.tools.withcontext.Packages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by Do on 2017/5/7.
 */

@RunWith(AndroidJUnit4.class)
public class PackagesTest
{
    @Before
    public void init()
    {
        Context context = InstrumentationRegistry.getTargetContext();
        Easy.getDefault().init(context);
    }

    @Test
    public void testOperationTime()
    {
        long l = System.currentTimeMillis();
        System.out.println(l);
        List<AppInfo> list = Packages.getInstalledAppsInfo();
        for(AppInfo appInfo : list)
        {
            Drawable icon = appInfo.getIcon();
        }
        long duration = System.currentTimeMillis() - l;
        System.out.println(duration);
    }
}
