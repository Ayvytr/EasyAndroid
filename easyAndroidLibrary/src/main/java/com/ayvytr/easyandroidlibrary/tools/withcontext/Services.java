package com.ayvytr.easyandroidlibrary.tools.withcontext;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.content.ClipboardManager;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.WindowManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.content.Context.ACCESSIBILITY_SERVICE;
import static android.content.Context.ACCOUNT_SERVICE;
import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.APPWIDGET_SERVICE;
import static android.content.Context.APP_OPS_SERVICE;
import static android.content.Context.AUDIO_SERVICE;
import static android.content.Context.BATTERY_SERVICE;
import static android.content.Context.BLUETOOTH_SERVICE;
import static android.content.Context.CAMERA_SERVICE;
import static android.content.Context.CAPTIONING_SERVICE;
import static android.content.Context.CARRIER_CONFIG_SERVICE;
import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.CONSUMER_IR_SERVICE;
import static android.content.Context.DISPLAY_SERVICE;
import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.HARDWARE_PROPERTIES_SERVICE;
import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;
import static android.content.Context.LAUNCHER_APPS_SERVICE;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MEDIA_ROUTER_SERVICE;
import static android.content.Context.MEDIA_SESSION_SERVICE;
import static android.content.Context.MIDI_SERVICE;
import static android.content.Context.NFC_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.NSD_SERVICE;
import static android.content.Context.POWER_SERVICE;
import static android.content.Context.PRINT_SERVICE;
import static android.content.Context.RESTRICTIONS_SERVICE;
import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.STORAGE_SERVICE;
import static android.content.Context.TELECOM_SERVICE;
import static android.content.Context.TELEPHONY_SERVICE;
import static android.content.Context.TELEPHONY_SUBSCRIPTION_SERVICE;
import static android.content.Context.TEXT_SERVICES_MANAGER_SERVICE;
import static android.content.Context.UI_MODE_SERVICE;
import static android.content.Context.USER_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static android.content.Context.WALLPAPER_SERVICE;
import static android.content.Context.WINDOW_SERVICE;
import static com.ayvytr.easyandroidlibrary.Easy.getContext;

/**
 * 系统服务类，可以方便的获取你需要的Android管理类.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.3
 */

public class Services
{
    @StringDef({
            POWER_SERVICE,
            WINDOW_SERVICE,
            LAYOUT_INFLATER_SERVICE,
            ACCOUNT_SERVICE,
            ACTIVITY_SERVICE,
            ALARM_SERVICE,
            NOTIFICATION_SERVICE,
            ACCESSIBILITY_SERVICE,
            CAPTIONING_SERVICE,
            KEYGUARD_SERVICE,
            LOCATION_SERVICE,
            SENSOR_SERVICE,
            STORAGE_SERVICE,
            WALLPAPER_SERVICE,
            VIBRATOR_SERVICE,
            NSD_SERVICE,
            AUDIO_SERVICE,
            FINGERPRINT_SERVICE,
            MEDIA_ROUTER_SERVICE,
            TELEPHONY_SERVICE,
            TELEPHONY_SUBSCRIPTION_SERVICE,
            CARRIER_CONFIG_SERVICE,
            TELECOM_SERVICE,
            CLIPBOARD_SERVICE,
            INPUT_METHOD_SERVICE,
            TEXT_SERVICES_MANAGER_SERVICE,
            APPWIDGET_SERVICE,
            UI_MODE_SERVICE,
            DOWNLOAD_SERVICE,
            NFC_SERVICE,
            BLUETOOTH_SERVICE,
            LAUNCHER_APPS_SERVICE,
            DISPLAY_SERVICE,
            USER_SERVICE,
            RESTRICTIONS_SERVICE,
            APP_OPS_SERVICE,
            CAMERA_SERVICE,
            PRINT_SERVICE,
            CONSUMER_IR_SERVICE,
            MEDIA_SESSION_SERVICE,
            BATTERY_SERVICE,
            JOB_SCHEDULER_SERVICE,
            MIDI_SERVICE,
            HARDWARE_PROPERTIES_SERVICE,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceName {}

    private static Object getSystemService(@ServiceName String serviceName)
    {
        return getContext().getSystemService(serviceName);
    }

    public static AccountManager getAccountManager()
    {
        return (AccountManager) getSystemService(ACCOUNT_SERVICE);
    }

    /**
     * 获取PackageManager
     *
     * @return PackageManager
     */
    public static PackageManager getPackageManager()
    {
        return getContext().getPackageManager();
    }

    /**
     * 获取 ClipboardManager
     *
     * @return ClipboardManager
     */
    public static ClipboardManager getClipboardManager()
    {
        return (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    /**
     * 获取WindowManager
     *
     * @return WindowManager
     */
    public static WindowManager getWindowManager()
    {
        return (WindowManager) getSystemService(WINDOW_SERVICE);
    }

    /**
     * 获取KeyguardManager
     *
     * @return KeyguardManager
     */
    public static KeyguardManager getKeyguardManager()
    {
        return (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
    }

    public static PowerManager getPowerManager()
    {
        return (PowerManager) getSystemService(POWER_SERVICE);
    }

    public static LayoutInflater getLayoutInflater()
    {
        return (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    public static ActivityManager getActivityManager()
    {
        return (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    public static AlarmManager getAlarmManager()
    {
        return (AlarmManager) getSystemService(ALARM_SERVICE);
    }

}
