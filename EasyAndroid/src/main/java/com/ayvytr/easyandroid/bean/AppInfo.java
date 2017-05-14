package com.ayvytr.easyandroid.bean;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.ayvytr.easyandroid.cache.AppInfoCache;
import com.ayvytr.easyandroid.tools.TextTool;

/**
 * 包含应用label，package name，isSystemApp等信息的Bean类(由于获取Drawable转Bitmap费时过长，icon不再存储，
 * 现在可通过 {@link #getIcon()} 获取Icon，或者 {@link #loadIconInto(ImageView)} 加载进 ImageView).
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.6
 */

public class AppInfo implements Parcelable, Comparable<AppInfo>
{
    public String label;
    public String packageName;
    public String className;
    public boolean isSystemApp;

    public AppInfo(String label, String packageName, String className, boolean isSystemApp)
    {
        this.label = label;
        this.packageName = packageName;
        this.className = className;
        this.isSystemApp = isSystemApp;
    }

    public AppInfo()
    {

    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.label);
        dest.writeString(this.packageName);
        dest.writeString(this.className);
        dest.writeByte(this.isSystemApp ? (byte) 1 : (byte) 0);
    }

    protected AppInfo(Parcel in)
    {
        this.label = in.readString();
        this.packageName = in.readString();
        this.className = in.readString();
        this.isSystemApp = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>()
    {
        @Override
        public AppInfo createFromParcel(Parcel source)
        {
            return new AppInfo(source);
        }

        @Override
        public AppInfo[] newArray(int size)
        {
            return new AppInfo[size];
        }
    };

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }

        if(!(obj instanceof AppInfo))
        {
            return false;
        }

        AppInfo ai = (AppInfo) obj;
        if(TextTool.notEquals(className, ai.className) ||
                TextTool.notEquals(label, ai.label) ||
                TextTool.notEquals(packageName, ai.packageName) ||
                isSystemApp != ai.isSystemApp)
        {
            return false;
        }

        return true;
    }

    /**
     * 获取 {@link #packageName} 的应用图标，使用 {@link AppInfoCache} 缓存，减少内存占用.
     *
     * @return {@link Drawable}
     */
    public Drawable getIcon()
    {
        return AppInfoCache.getIconDrawable(packageName);
    }

    /**
     * 加载应用图标到iv中.
     *
     * @param iv 目标 ImageView
     */
    public void loadIconInto(ImageView iv)
    {
        Drawable icon = getIcon();
        if(icon != null)
        {
            iv.setImageDrawable(icon);
        }
    }

    @Override
    public int compareTo(@NonNull AppInfo o)
    {
        if(this == o)
        {
            return 0;
        }

        if(TextTool.isNotEmpty(packageName) && TextTool.notEmpty(o.packageName))
        {
            return packageName.compareTo(o.packageName);
        }

        if(TextTool.isNotEmpty(className) && TextTool.notEmpty(o.className))
        {
            return className.compareTo(o.className);
        }

        if(TextTool.isNotEmpty(label) && TextTool.notEmpty(o.label))
        {
            return label.compareTo(o.label);
        }

        return 0;
    }
}
