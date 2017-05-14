package com.ayvytr.easyandroid.tools.withcontext;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.ayvytr.easyandroid.Easy;
import com.ayvytr.easyandroid.bean.AppInfo;
import com.ayvytr.easyandroid.cache.AppInfoCache;

import java.util.List;

/**
 * 获取安装的应用程序等功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.6
 */

public class Packages
{
    /**
     * 获取系统中安装的应用
     */
    public static List<PackageInfo> getInstalledPackages()
    {
        return Easy.getContext().getPackageManager().getInstalledPackages(0);
    }

    /**
     * 获取系统中安装的应用
     */
    public static List<ApplicationInfo> getInstalledApplications()
    {
        return Easy.getContext().getPackageManager().getInstalledApplications(0);
    }

    /**
     * 获取返回安装的应用, 详情见{@link AppInfo}.
     *
     * @return {@link AppInfo} 列表
     */
    public static List<AppInfo> getInstalledAppsInfo()
    {
        return AppInfoCache.getAppsInfo();
    }

}
