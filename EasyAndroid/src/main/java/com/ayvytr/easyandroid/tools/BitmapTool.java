package com.ayvytr.easyandroid.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import com.ayvytr.easyandroid.tools.withcontext.ResTool;

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
     * drawable 转换为 Bitmap.
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    private static Bitmap toBitmap1(Drawable drawable)
    {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    /**
     * drawable 转换为 Bitmap.
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    private static Bitmap toBitmap2(Drawable drawable)
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
     * Drawable 转换返回 Bitmap.
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    public static Bitmap toBitmap(Drawable drawable)
    {
        try
        {
            /*
             直接强转BitmapDrawable有时报错：
             java.lang.ClassCastException: android.graphics.drawable.VectorDrawable
             cannot be cast to android.graphics.drawable.BitmapDrawable
             */
            return toBitmap1(drawable);
        } catch(Exception e)
        {
            e.printStackTrace();
            return toBitmap2(drawable);
        }
    }

    /**
     * Drawable 转换为Bitmap.
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
     * byte[] 转换为 Bitmap.
     *
     * @param bytes byte[]
     * @return Bitmap
     */
    public static Bitmap toBitmap(byte[] bytes)
    {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * byte[] 转换为 Drawable.
     *
     * @param bytes byte[]
     * @return Drawable
     */
    public static Drawable toDrawable(byte[] bytes)
    {
        return toDrawable(toBitmap(bytes));
    }

    /**
     * Bitmap 转换为 Drawable.
     *
     * @param bitmap Bitmap
     * @return Drawable
     */
    public static Drawable toDrawable(Bitmap bitmap)
    {
        return new BitmapDrawable(ResTool.getResources(), bitmap);
    }

    /**
     * Drawable 转换为 byte[].
     *
     * @param drawable Drawable
     * @return byte[]
     */
    public static byte[] toByteArray(Drawable drawable)
    {
        return toByteArray(toBitmap(drawable));
    }

    /**
     * Bitmap 转换为 byte[].
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

    /**
     * 按照指定宽高缩放图片，并返回.
     *
     * @param bitmap    图片
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @return 新图片
     */
    public static Bitmap zoom(Bitmap bitmap, int newWidth, int newHeight)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 按照指定宽高缩放图片，并返回.
     *
     * @param drawable  图片
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @return 新图片
     */
    public static Bitmap zoom(Drawable drawable, int newWidth, int newHeight)
    {
        return zoom(toBitmap(drawable), newWidth, newHeight);
    }

    /**
     * 按照指定百分比缩放图片，并返回，内部按照percentage / 100 作为百分比，对图片宽高等比例缩放.
     *
     * @param bitmap     图片
     * @param percentage 缩放百分比 {@code percent = percentage / 100}
     * @return 新图片
     */
    public static Bitmap zoom(Bitmap bitmap, int percentage)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = percentage / 100f;
        float scaleHeight = percentage / 100f;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 按照指定百分比缩放图片，并返回，内部按照percentage / 100 作为百分比，对图片宽高等比例缩放.
     *
     * @param drawable   图片
     * @param percentage 缩放百分比 {@code percent = percentage / 100}
     * @return 新图片
     */
    public static Bitmap zoom(Drawable drawable, int percentage)
    {
        return zoom(toBitmap(drawable), percentage);
    }

    /**
     * 按照指定宽高缩放图片，并返回.
     *
     * @param bitmap    图片
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @return 新图片
     */
    public static Drawable zoom2Drawable(Bitmap bitmap, int newWidth, int newHeight)
    {
        return toDrawable(zoom(bitmap, newWidth, newHeight));
    }

    /**
     * 按照指定宽高缩放图片，并返回.
     *
     * @param drawable  图片
     * @param newWidth  新宽度
     * @param newHeight 新高度
     * @return 新图片
     */
    public static Drawable zoom2Drawable(Drawable drawable, int newWidth, int newHeight)
    {
        return toDrawable(zoom(toBitmap(drawable), newWidth, newHeight));
    }

    /**
     * 按照指定百分比缩放图片，并返回，内部按照percentage / 100 作为百分比，对图片宽高等比例缩放.
     *
     * @param bitmap     图片
     * @param percentage 缩放百分比 {@code percent = percentage / 100}
     * @return 新图片
     */
    public static Drawable zoom2Drawable(Bitmap bitmap, int percentage)
    {
        return toDrawable(zoom(bitmap, percentage));
    }

    /**
     * 按照指定百分比缩放图片，并返回，内部按照percentage / 100 作为百分比，对图片宽高等比例缩放.
     *
     * @param drawable   图片
     * @param percentage 缩放百分比 {@code percent = percentage / 100}
     * @return 新图片
     */
    public static Drawable zoom2Drawable(Drawable drawable, int percentage)
    {
        return toDrawable(zoom(toBitmap(drawable), percentage));
    }

    /**
     * 按照指定角度旋转图片，并返回.
     *
     * @param bitmap  图片
     * @param degrees 旋转角度
     * @return 新图片
     */
    public static Bitmap rotate(Bitmap bitmap, int degrees)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 按照指定角度旋转图片，并返回.
     *
     * @param drawable 图片
     * @param degrees  旋转角度
     * @return 新图片
     */
    public static Bitmap rotate(Drawable drawable, int degrees)
    {
        return rotate(toBitmap(drawable), degrees);
    }

    /**
     * 按照指定角度旋转图片，并返回.
     *
     * @param bitmap  图片
     * @param degrees 旋转角度
     * @return 新图片
     */
    public static Drawable rotate2Drawable(Bitmap bitmap, int degrees)
    {
        return toDrawable(rotate(bitmap, degrees));
    }

    /**
     * 按照指定角度旋转图片，并返回.
     *
     * @param drawable 图片
     * @param degrees  旋转角度
     * @return 新图片
     */
    public static Drawable rotate2Drawable(Drawable drawable, int degrees)
    {
        return toDrawable(rotate(toBitmap(drawable), degrees));
    }
}
