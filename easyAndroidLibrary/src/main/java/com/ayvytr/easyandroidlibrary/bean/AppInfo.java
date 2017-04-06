package com.ayvytr.easyandroidlibrary.bean;

import android.graphics.drawable.Drawable;

/**
 * Desc:
 * Date: 2017/4/6
 *
 * @author davidwang
 */

public class AppInfo
{
    public String label;
    public String packageName;
    public Drawable icon;
    public String className;
    public boolean isSystemApp;

    public AppInfo(String label, String packageName, Drawable icon, String className,
                   boolean isSystemApp)
    {
        this.label = label;
        this.packageName = packageName;
        this.icon = icon;
        this.className = className;
        this.isSystemApp = isSystemApp;
    }
}
