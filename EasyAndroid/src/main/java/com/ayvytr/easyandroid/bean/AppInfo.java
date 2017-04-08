package com.ayvytr.easyandroid.bean;

import android.graphics.drawable.Drawable;

/**
 * 包含应用label，package name，icon 等信息的Bean类.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.5
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
