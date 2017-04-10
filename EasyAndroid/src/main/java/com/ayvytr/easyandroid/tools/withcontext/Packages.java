package com.ayvytr.easyandroid.tools.withcontext;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ayvytr.easyandroid.Easy;
import com.ayvytr.easyandroid.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取安装的应用程序等功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.6
 */

public class Packages
{
    public static List<PackageInfo> getInstalledPackages()
    {
        return Easy.getContext().getPackageManager().getInstalledPackages(0);
    }

    public static List<ApplicationInfo> getInstalledApplications()
    {
        return Easy.getContext().getPackageManager().getInstalledApplications(0);
    }

    public static List<AppInfo> getInstalledAppsInfo()
    {
        PackageManager pm = Easy.getContext().getPackageManager();
        List<AppInfo> list = new ArrayList<>();
        List<PackageInfo> packages = getInstalledPackages();
        for(PackageInfo p : packages)
        {
            p.applicationInfo.loadIcon(pm);
            p.applicationInfo.loadLabel(pm);
            list.add(new AppInfo(p.applicationInfo.loadLabel(pm).toString(),
                    p.applicationInfo.packageName,
                    p.applicationInfo.loadIcon(pm),
                    p.applicationInfo.className,
                    (p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0));
        }
        return list;
    }

}
