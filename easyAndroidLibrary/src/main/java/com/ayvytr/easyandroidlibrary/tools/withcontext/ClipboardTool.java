package com.ayvytr.easyandroidlibrary.tools.withcontext;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;

import com.ayvytr.easyandroidlibrary.Easy;
import com.ayvytr.easyandroidlibrary.exception.UnsupportedInitializationException;

import static com.ayvytr.easyandroidlibrary.tools.withcontext.Managers.getClipboardManager;

/**
 * 剪贴板操作类，从剪贴板中获取/设置文本，Intent，Uri.
 * <p>
 * 因为剪贴板需要的SDK最小是11，所以设置了 minSdkVersion 为 11.
 * </p>
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class ClipboardTool
{
    private static final String TEXT = "text";
    private static final String URI = "uri";
    private static final String Intent = "intent";

    private ClipboardTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 设置文本到剪贴板
     *
     * @param text 文本
     */
    public static void setText(CharSequence text)
    {
        ClipboardManager clipboardManager = getClipboardManager();
        clipboardManager.setPrimaryClip(ClipData.newPlainText(TEXT, text));
    }

    /**
     * 获取剪贴板的文本
     *
     * @return 剪贴板的文本
     */
    public static String getText()
    {
        return getText(null);
    }

    /**
     * 获取剪贴板文本，当获取到是空时，返回默认字符串
     *
     * @param textIfNull 剪贴板文本如果为空，返回这个字符串
     * @return 剪贴板文本
     */
    public static String getText(String textIfNull)
    {
        ClipboardManager clipboardManager = getClipboardManager();
        ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData != null && clipData.getItemCount() > 0)
        {
            return clipData.getItemAt(0).coerceToText(Easy.getContext()).toString();
        }

        return textIfNull;
    }

    /**
     * 设置uri到剪贴板
     *
     * @param uri uri
     */
    public static void setUri(Uri uri)
    {
        ClipboardManager clipboardManager = Managers.getClipboardManager();
        clipboardManager.setPrimaryClip(
                ClipData.newUri(Easy.getContext().getContentResolver(), URI, uri));
    }

    /**
     * 获取剪贴板的uri
     *
     * @return 剪贴板的uri
     */
    public static Uri getUri()
    {
        ClipboardManager clipboardManager = Managers.getClipboardManager();
        ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData != null && clipData.getItemCount() > 0)
        {
            return clipData.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * 设置意图到剪贴板
     *
     * @param intent 意图
     */
    public static void setIntent(Intent intent)
    {
        ClipboardManager clipboardManager = Managers.getClipboardManager();
        clipboardManager.setPrimaryClip(ClipData.newIntent(Intent, intent));
    }

    /**
     * 获取剪贴板的意图
     *
     * @return 剪贴板的意图
     */
    public static Intent getIntent()
    {
        ClipboardManager clipboardManager = Managers.getClipboardManager();
        ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData != null && clipData.getItemCount() > 0)
        {
            return clipData.getItemAt(0).getIntent();
        }
        return null;
    }
}
