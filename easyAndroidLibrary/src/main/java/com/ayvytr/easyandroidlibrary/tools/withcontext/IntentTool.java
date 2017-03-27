package com.ayvytr.easyandroidlibrary.tools.withcontext;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import com.ayvytr.easyandroidlibrary.Easy;
import com.ayvytr.easyandroidlibrary.exception.UnsupportedInitializationException;
import com.ayvytr.easyandroidlibrary.tools.FileTool;

import java.io.File;
import java.util.List;

/**
 * 获取常用的Intent.
 * <p>
 * 原作者：blankj
 * blog  : http://blankj.com
 * </p>
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */
public class IntentTool
{

    private IntentTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 获取安装App（支持6.0）的意图
     *
     * @param filePath 文件路径
     * @return intent
     */
    public static Intent getInstallAppIntent(String filePath)
    {
        return getInstallAppIntent(FileTool.fromName(filePath));
    }

    /**
     * 获取安装App(支持6.0)的意图
     *
     * @param file 文件
     * @return intent
     */
    public static Intent getInstallAppIntent(File file)
    {
        if(file == null) return null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type;

        if(Build.VERSION.SDK_INT < 23)
        {
            type = "application/vnd.android.package-archive";
        }
        else
        {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileTool.getExtension(file));
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider
                    .getUriForFile(Easy.getContext(), "com.your.package.fileProvider", file);
            intent.setDataAndType(contentUri, type);
        }
        intent.setDataAndType(Uri.fromFile(file), type);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取卸载App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName)
    {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(String packageName)
    {
        return Easy.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取App具体设置的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName)
    {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享文本的意图
     *
     * @param content 分享文本
     * @return intent
     */
    public static Intent getShareTextIntent(String content)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取分享图片的意图
     *
     * @param content   文本
     * @param imagePath 图片文件路径
     * @return intent
     */
    public static Intent getShareImageIntent(String content, String imagePath)
    {
        return getShareImageIntent(content, FileTool.fromName(imagePath));
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 文本
     * @param image   图片文件
     * @return intent
     */
    public static Intent getShareImageIntent(String content, File image)
    {
        if(!FileTool.isExists(image))
        {
            return null;
        }

        return getShareImageIntent(content, Uri.fromFile(image));
    }

    /**
     * 获取分享图片的意图
     *
     * @param content 分享文本
     * @param uri     图片uri
     * @return intent
     */
    public static Intent getShareImageIntent(String content, Uri uri)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        return intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className)
    {
        return getComponentIntent(packageName, className, null);
    }

    /**
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if(bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取关机的意图
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.SHUTDOWN"/>}</p>
     *
     * @return intent
     */
    public static Intent getShutdownIntent()
    {
        Intent intent = new Intent(Intent.ACTION_SHUTDOWN);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取跳至拨号界面意图
     *
     * @param phoneNumber 电话号码
     * @return Intent
     */
    public static Intent getDialIntent(String phoneNumber)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取拨打电话意图
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param phoneNumber 电话号码
     * @return Intent
     */
    public static Intent getCallIntent(String phoneNumber)
    {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取跳至发送短信界面的意图
     *
     * @param phoneNumber 接收号码
     * @param content     短信内容
     * @return Intent
     */
    public static Intent getSendSmsIntent(String phoneNumber, String content)
    {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    /**
     * 获取拍照的意图
     *
     * @param outUri 输出的uri
     * @return 拍照的意图
     */
    public static Intent getCaptureIntent(Uri outUri)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        return intent
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取可以响应intent的Activity列表.<br>
     * {@code null} 没有可以相应这个intent的Activity.
     *
     * @param intent Intent
     * @return 可以响应intent的Activity列表
     */
    public static List<ResolveInfo> queryIntentActivities(Intent intent)
    {
        PackageManager packageManager = Easy.getContext().getPackageManager();
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    }
}
