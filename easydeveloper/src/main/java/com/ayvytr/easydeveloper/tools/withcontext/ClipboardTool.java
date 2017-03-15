package com.ayvytr.easydeveloper.tools.withcontext;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;

import com.ayvytr.easydeveloper.Easy;
import com.ayvytr.easydeveloper.consts.EasyConst;
import com.ayvytr.easydeveloper.exception.UnsupportedInitializationException;

/**
 * Created by davidwang on 2017/3/15.
 */

public class ClipboardTool
{
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
        ClipboardManager clipboardManager = Easy.getDefault().getClipboardManager();
        clipboardManager.setPrimaryClip(ClipData.newPlainText(EasyConst.TEXT, text));
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
        ClipboardManager clipboardManager = Easy.getDefault().getClipboardManager();
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
        ClipboardManager clipboardManager = Easy.getDefault().getClipboardManager();
        clipboardManager.setPrimaryClip(
                ClipData.newUri(Easy.getContext().getContentResolver(), EasyConst.Uri, uri));
    }

    /**
     * 获取剪贴板的uri
     *
     * @return 剪贴板的uri
     */
    public static Uri getUri()
    {
        ClipboardManager clipboardManager = Easy.getDefault().getClipboardManager();
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
        ClipboardManager clipboardManager = Easy.getDefault().getClipboardManager();
        clipboardManager.setPrimaryClip(ClipData.newIntent(EasyConst.Intent, intent));
    }

    /**
     * 获取剪贴板的意图
     *
     * @return 剪贴板的意图
     */
    public static Intent getIntent()
    {
        ClipboardManager clipboardManager = Easy.getDefault().getClipboardManager();
        ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData != null && clipData.getItemCount() > 0)
        {
            return clipData.getItemAt(0).getIntent();
        }
        return null;
    }
}
