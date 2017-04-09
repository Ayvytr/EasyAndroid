package com.ayvytr.easyandroid.tools.withcontext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 提供Bitmap，Drawable，byte[]的相互转换等图片转换功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.6
 */

public class BitmapTool
{

    /**
     * drawable 转换为 Bitmap
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    public static Bitmap toBitmap(Drawable drawable)
    {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    /**
     * drawable 转换为 Bitmap
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    public static Bitmap toBitmap2(Drawable drawable)
    {
        Bitmap bitmap;
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Drawable 转换为Bitmap
     *
     * @param id Drawable id
     * @return Bitmap
     */
    public static Bitmap toBitmap(@DrawableRes int id)
    {
        Drawable drawable = ResTool.getDrawable(id);
        return toBitmap(drawable);
    }

    /**
     * Drawable 转换为Bitmap
     *
     * @param id Drawable id
     * @return Bitmap
     */
    public static Bitmap toBitmap2(@DrawableRes int id)
    {
        Drawable drawable = ResTool.getDrawable(id);
        return toBitmap2(drawable);
    }

    /**
     * byte[] 转换为 Bitmap
     *
     * @param bytes byte[]
     * @return Bitmap
     */
    public static Bitmap toBitmap(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * byte[] 转换为 Drawable
     *
     * @param bytes byte[]
     * @return Drawable
     */
    public static Drawable toDrawable(byte[] bytes)
    {
        return toDrawable(toBitmap(bytes));
    }

    /**
     * Bitmap 转换为 Drawable
     *
     * @param bitmap Bitmap
     * @return Drawable
     */
    public static Drawable toDrawable(Bitmap bitmap)
    {
        return new BitmapDrawable(ResTool.getResources(), bitmap);
    }

    /**
     * Drawable 转换为 byte[]
     *
     * @param drawable Drawable
     * @return byte[]
     */
    public static byte[] toByteArray(Drawable drawable)
    {
        return toByteArray(toBitmap(drawable));
    }

    /**
     * Bitmap 转换为 byte[]
     *
     * @param bitmap Bitmap
     * @return byte[]
     */
    public static byte[] toByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);
        byte[] bytes = b.toByteArray();
        try
        {
            b.close();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        return bytes;
    }
}
