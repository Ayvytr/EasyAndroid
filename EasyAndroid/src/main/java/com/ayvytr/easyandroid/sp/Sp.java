package com.ayvytr.easyandroid.sp;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * {@link Sp}, 是 {@link SharedPreferences} 的操作类，通过构造函数接受了 {@link SharedPreferences} 实例，获取值和设置值的步骤
 * 进行了简化和缩短，能够较为简便地使用。这个类可以单独使用，不过还是建议使用 {@link SpManager} 获得 {@link Sp} 实例，便于统一管
 * 理，以及统一释放 {@link SpManager#close()}.
 * <p>
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 2.0.0
 */

public class Sp
{
    protected SharedPreferences sp;

    public Sp(@NonNull SharedPreferences sharedPreferences)
    {
        sp = sharedPreferences;
    }

    /**
     * 获取 {@link SharedPreferences} 实例
     *
     * @return {@link SharedPreferences}
     */
    public SharedPreferences getSp()
    {
        return sp;
    }


    /**
     * @param key key
     * @return value
     * @see #getString(String, String)
     */
    public String getString(String key)
    {
        return getString(key, "");
    }

    /**
     * @param key key
     * @return value
     * @see SharedPreferences#getString(String, String)
     */
    @Nullable
    public String getString(String key, @Nullable String defStr)
    {
        return sp.getString(key, defStr);
    }

    /**
     * @param key key
     * @return value
     * @see #getInt(String, int)
     */
    public int getInt(@NonNull String key)
    {
        return getInt(key, 0);
    }

    /**
     * @param key key
     * @return value
     * @see SharedPreferences#getInt(String, int)
     */
    public int getInt(String key, int defInt)
    {
        return sp.getInt(key, defInt);
    }

    /**
     * @param key key
     * @return value
     * @see #getBool(String, boolean)
     */
    public boolean getBool(String key)
    {
        return getBool(key, false);
    }

    /**
     * @param key key
     * @return value
     * @see SharedPreferences#getBoolean(String, boolean)
     */
    public boolean getBool(String key, boolean defBool)
    {
        return sp.getBoolean(key, defBool);
    }

    /**
     * @param key key
     * @return value
     * @see #getFloat(String)
     */
    public float getFloat(String key)
    {
        return getFloat(key, 0F);
    }

    /**
     * @param key key
     * @return value
     * @see SharedPreferences#getFloat(String, float)
     */
    public float getFloat(String key, float defFloat)
    {
        return sp.getFloat(key, defFloat);
    }

    /**
     * @param key key
     * @return value
     * @see #getLong(String, long)
     */
    public long getLong(String key)
    {
        return getLong(key, 0);
    }

    /**
     * @param key key
     * @return value
     * @see SharedPreferences#getLong(String, long)
     */
    public long getLong(String key, long defLong)
    {
        return sp.getLong(key, defLong);
    }

    /**
     * @param key key
     * @return value
     * @see #getStringSet(String, Set)
     */
    public Set<String> getStringSet(String key)
    {
        return getStringSet(key, null);
    }

    /**
     * @param key key
     * @return value
     * @see SharedPreferences#getStringSet(String, Set)
     */
    public Set<String> getStringSet(String key, Set<String> defStringSet)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            return sp.getStringSet(key, defStringSet);
        }

        return null;
    }

    /**
     * @return value
     * @see SharedPreferences#getAll()
     */
    public Map<String, ?> getAll()
    {
        return sp.getAll();
    }

    /**
     * @param key key
     * @return {@code true } 存在键名为key的值
     * @see SharedPreferences#contains(String)
     */
    public boolean contains(String key)
    {
        return sp.contains(key);
    }

    /**
     * @param key key
     * @param value value
     * @return {@link Sp}
     * @see SharedPreferences.Editor#putString(String, String)
     */
    public Sp putString(String key, String value)
    {
        sp.edit().putString(key, value).apply();
        return this;
    }

    /**
     * @param key key
     * @param value value
     * @return {@link Sp}
     * @see SharedPreferences.Editor#putInt(String, int)
     */
    public Sp putInt(String key, int value)
    {
        sp.edit().putInt(key, value).apply();
        return this;
    }

    /**
     * @param key key
     * @param value value
     * @return {@link Sp}
     * @see SharedPreferences.Editor#putLong(String, long)
     */
    public Sp putLong(String key, long value)
    {
        sp.edit().putLong(key, value).apply();
        return this;
    }

    /**
     * @param key key
     * @param value value
     * @return {@link Sp}
     * @see SharedPreferences.Editor#putBoolean(String, boolean)
     */
    public Sp putBool(String key, boolean value)
    {
        sp.edit().putBoolean(key, value).apply();
        return this;
    }

    /**
     * @param key key
     * @param value value
     * @return {@link Sp}
     * @see SharedPreferences.Editor#putFloat(String, float)
     */
    public Sp putFloat(String key, float value)
    {
        sp.edit().putFloat(key, value).apply();
        return this;
    }

    /**
     * @param key key
     * @param value value
     * @return {@link Sp}
     * @see SharedPreferences.Editor#putStringSet(String, Set)
     */
    public Sp putStringSet(String key, Set<String> value)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            sp.edit().putStringSet(key, value).apply();
        }
        return this;
    }

    /**
     * @param key key
     * @see SharedPreferences.Editor#remove(String)
     */
    public void remove(String key)
    {
        sp.edit().remove(key).apply();
    }

    /**
     * @see SharedPreferences.Editor#clear()
     */
    public void clear()
    {
        sp.edit().clear().apply();
    }
}
