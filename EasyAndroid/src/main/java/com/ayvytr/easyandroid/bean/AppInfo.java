package com.ayvytr.easyandroid.bean;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.ayvytr.easyandroid.tools.BitmapTool;

/**
 * 包含应用label，package name，icon 等信息的Bean类.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.6
 */

public class AppInfo implements Parcelable
{
    public String label;
    public String packageName;
    public Bitmap icon;
    public String className;
    public boolean isSystemApp;

    public AppInfo(String label, String packageName, Drawable icon, String className,
                   boolean isSystemApp)
    {
        this.label = label;
        this.packageName = packageName;
        this.icon = BitmapTool.toBitmap(icon);
        this.className = className;
        this.isSystemApp = isSystemApp;
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
        dest.writeParcelable(this.icon, flags);
        dest.writeString(this.className);
        dest.writeByte(this.isSystemApp ? (byte) 1 : (byte) 0);
    }

    protected AppInfo(Parcel in)
    {
        this.label = in.readString();
        this.packageName = in.readString();
        this.icon = in.readParcelable(Bitmap.class.getClassLoader());
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
}
