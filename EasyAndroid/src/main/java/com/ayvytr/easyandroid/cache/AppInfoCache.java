package com.ayvytr.easyandroid.cache;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.ayvytr.easyandroid.bean.AppInfo;
import com.ayvytr.easyandroid.tools.withcontext.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ayvytr.easyandroid.tools.withcontext.Packages.getInstalledPackages;


/**
 * 主要为 {@link com.ayvytr.easyandroid.bean.AppInfo} 类提供，解决使用 AppInfo 类获取Drawable未缓存，app内存占用过多的问题，
 * 缓存了 PackageManager，
 */

public class AppInfoCache
{
    private static PackageManager packageManager;

    private static Map<String, Drawable> map;

    private static List<AppInfo> list;

    public static PackageManager getPackageManager()
    {
        if(packageManager == null)
        {
            packageManager = Managers.getPackageManager();
        }

        return packageManager;
    }

    public static Drawable getIconDrawable(String packageName)
    {
        if(map == null)
        {
            map = new HashMap<>();
        }

        Drawable drawable = map.get(packageName);
        if(drawable == null)
        {
            drawable = getIcon(packageName);
            map.put(packageName, drawable);
        }

        return drawable;
    }

    private static Drawable getIcon(String packageName)
    {
        try
        {
            return packageManager.getPackageInfo(packageName, 0).applicationInfo
                    .loadIcon(packageManager);
        } catch(PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AppInfo> getAppsInfo()
    {
        PackageManager pm = AppInfoCache.getPackageManager();
        List<PackageInfo> packages = getInstalledPackages();
        if(list == null)
        {
            list = new ArrayList<>(packages.size());
        }

        int i, count;
        for(i = 0, count = list.size(); i < count; i++)
        {
            ApplicationInfo appInfo = packages.get(i).applicationInfo;
            AppInfo a = list.get(i);

            if(a.packageName.equals(appInfo.packageName))
            {
                continue;
            }

            a.label = appInfo.loadLabel(pm).toString();
            a.packageName = appInfo.packageName;
            a.className = appInfo.className;
            a.isSystemApp = (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        }

        count = packages.size();
        for(; i < count; i++)
        {
            ApplicationInfo appInfo = packages.get(i).applicationInfo;
            list.add(new AppInfo(appInfo.loadLabel(pm).toString(), appInfo.packageName,
                    appInfo.className, (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0));
        }

        return list;
    }
}
