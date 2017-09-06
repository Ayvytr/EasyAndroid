package com.ayvytr.easyandroid.sp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * {@link SpManager}, 是 {@link Sp} 的管理类，同时也是 {@link android.content.SharedPreferences} 的操作类，以单例模式统一管理
 * 了 {@link Sp} 实例，便于统一维护管理和释放.
 * <p>
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 2.0.0
 */

public class SpManager
{
    private volatile static SpManager spManager;

    /**
     * {@link Sp} 集合
     */
    private static HashMap<String, Sp> map = new LinkedHashMap<>();

    private Context context;

    private SpManager(@NonNull Context context)
    {
        this.context = context;
    }

    public static SpManager getDefault(@NonNull Context context)
    {
        if(spManager == null)
        {
            synchronized(SpManager.class)
            {
                if(spManager == null)
                {
                    spManager = new SpManager(context);
                }
            }
        }

        return spManager;
    }

    /**
     * 获取默认的 {@link Sp}，默认名称为当前app的包名 {@link Context#getPackageName()}
     *
     * @return {@link Sp}
     */
    public Sp getSp()
    {
        Sp sp = map.get(context.getPackageName());
        if(sp == null)
        {
            sp = new Sp(context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE));
            map.put(context.getPackageName(), sp);
        }

        return sp;
    }

    /**
     * 获取 {@link Sp} 实例
     *
     * @param name {@link android.content.SharedPreferences} 名称
     * @return {@link Sp}
     */
    public Sp getSp(@NonNull String name)
    {
        Sp sp = map.get(name);
        if(sp == null)
        {
            sp = new Sp(context.getSharedPreferences(name, Context.MODE_PRIVATE));
            map.put(name, sp);
        }

        return sp;
    }

    /**
     * 关闭 {@link SpManager}
     */
    public void close()
    {
        Set<String> keySet = map.keySet();
        for(String s : keySet)
        {
            map.remove(s);
        }

        map = null;
        context = null;
        spManager = null;
    }

    /**
     * 删除默认的 {@link android.content.SharedPreferences} 文件，默认文件名称为当前app包名 {@link Context#getPackageName()}
     */
    public void deleteSp()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            context.deleteSharedPreferences(context.getPackageName());
        }
    }

    /**
     * 删除名称为name的 {@link android.content.SharedPreferences} 文件
     *
     * @param name {@link android.content.SharedPreferences} 文件名
     */
    public void deleteSp(String name)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            context.deleteSharedPreferences(name);
        }
    }

    /**
     * 把指定位置和名称的 {@link android.content.SharedPreferences} 文件移动到当前app环境
     *
     * @param sourceContext 原位置
     * @param name          {@link android.content.SharedPreferences} 文件名
     */
    public void moveSpFrom(Context sourceContext, String name)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            context.moveSharedPreferencesFrom(sourceContext, name);
        }
    }
}
