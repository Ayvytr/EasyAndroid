package com.ayvytr.easyandroid.tools.withcontext;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.job.JobScheduler;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.MidiManager;
import android.media.session.MediaSessionManager;
import android.net.nsd.NsdManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.HardwarePropertiesManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.annotation.StringDef;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;

import com.ayvytr.easyandroid.exception.ClipboardUnsupportedException;

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
import static android.content.Context.TEXT_SERVICES_MANAGER_SERVICE;
import static android.content.Context.UI_MODE_SERVICE;
import static android.content.Context.USER_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static android.content.Context.WALLPAPER_SERVICE;
import static android.content.Context.WINDOW_SERVICE;
import static com.ayvytr.easyandroid.Easy.getContext;

/**
 * 系统服务类，可以方便的获取你需要的Android管理类.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.3
 */

public class Managers
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
    private @interface ServiceName {}

    private static Object get(@ServiceName String serviceName)
    {
        return getContext().getSystemService(serviceName);
    }

    public static AccountManager getAccountManager()
    {
        return (AccountManager) get(ACCOUNT_SERVICE);
    }

    public static PackageManager getPackageManager()
    {
        return getContext().getPackageManager();
    }

    public static ClipboardManager getClipboardManager()
    {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
        {
            throw new ClipboardUnsupportedException();
        }
        return (ClipboardManager) get(CLIPBOARD_SERVICE);
    }

    public static WindowManager getWindowManager()
    {
        return (WindowManager) get(WINDOW_SERVICE);
    }

    public static KeyguardManager getKeyguardManager()
    {
        return (KeyguardManager) get(KEYGUARD_SERVICE);
    }

    public static PowerManager getPowerManager()
    {
        return (PowerManager) get(POWER_SERVICE);
    }

    public static LayoutInflater getLayoutInflater()
    {
        return (LayoutInflater) get(LAYOUT_INFLATER_SERVICE);
    }

    public static ActivityManager getActivityManager()
    {
        return (ActivityManager) get(ACTIVITY_SERVICE);
    }

    public static AlarmManager getAlarmManager()
    {
        return (AlarmManager) get(ALARM_SERVICE);
    }

    public static NotificationManager getNotificationManager()
    {
        return (NotificationManager) get(NOTIFICATION_SERVICE);
    }

    public static AccessibilityManager getAccessibilityManager()
    {
        return (AccessibilityManager) get(ACCESSIBILITY_SERVICE);
    }

    public static CaptioningManager getCaptioningManager()
    {
        return (CaptioningManager) getContext().getSystemService(CAPTIONING_SERVICE);
    }

    public static LocationManager getLocationManager()
    {
        return (LocationManager) get(LOCATION_SERVICE);
    }

    public static SensorManager getSensorManager()
    {
        return (SensorManager) get(SENSOR_SERVICE);
    }

    public static StorageManager getStorageManage()
    {
        return (StorageManager) get(STORAGE_SERVICE);
    }

    public static WallpaperManager getWallpaperManager()
    {
        return (WallpaperManager) get(WALLPAPER_SERVICE);
    }

    public static Vibrator getVibrator()
    {
        return (Vibrator) get(VIBRATOR_SERVICE);
    }

    public static NsdManager getNsdManager()
    {
        return (NsdManager) get(NSD_SERVICE);
    }

    public static AudioManager getAudioManager()
    {
        return (AudioManager) get(AUDIO_SERVICE);
    }

    public static FingerprintManager getFingerprintManager()
    {
        return (FingerprintManager) get(FINGERPRINT_SERVICE);
    }

    public static MediaRouter getMediaRouter()
    {
        return (MediaRouter) get(MEDIA_ROUTER_SERVICE);
    }

    public static TelephonyManager getTelephonyManager()
    {
        return (TelephonyManager) get(TELEPHONY_SERVICE);
    }

    public static CarrierConfigManager getCarrierConfigManager()
    {
        return (CarrierConfigManager) get(CARRIER_CONFIG_SERVICE);
    }

    public static TelecomManager getTelecomManager()
    {
        return (TelecomManager) get(TELECOM_SERVICE);
    }

    public static InputMethodManager getInputMethodManager()
    {
        return (InputMethodManager) get(INPUT_METHOD_SERVICE);
    }

    public static TextServicesManager getTextServicesManager()
    {
        return (TextServicesManager) get(TEXT_SERVICES_MANAGER_SERVICE);
    }

    public static AppWidgetManager getAppWidgetManager()
    {
        return (AppWidgetManager) get(APPWIDGET_SERVICE);
    }

    public static UiModeManager getUiModeManager()
    {
        return (UiModeManager) get(UI_MODE_SERVICE);
    }

    public static DownloadManager getDownloadManager()
    {
        return (DownloadManager) get(DOWNLOAD_SERVICE);
    }

    public static NfcManager getNfcManager()
    {
        return (NfcManager) get(NFC_SERVICE);
    }

    public static BluetoothManager getBluetoothManager()
    {
        return (BluetoothManager) get(BLUETOOTH_SERVICE);
    }

    public static LauncherApps getLauncherApps()
    {
        return (LauncherApps) get(LAUNCHER_APPS_SERVICE);
    }

    public static DisplayManager getDisplayManagerCompat()
    {
        return (DisplayManager) get(DISPLAY_SERVICE);
    }

    public static UserManager getUserManager()
    {
        return (UserManager) get(USER_SERVICE);
    }

    public static RestrictionsManager getRestrictionsManager()
    {
        return (RestrictionsManager) get(RESTRICTIONS_SERVICE);
    }

    public static AppOpsManager getAppOpsManager()
    {
        return (AppOpsManager) get(APP_OPS_SERVICE);
    }

    public static CameraManager getCameraManager()
    {
        return (CameraManager) get(CAMERA_SERVICE);
    }

    public static PrintManager getPrintManager()
    {
        return (PrintManager) get(PRINT_SERVICE);
    }

    public static ConsumerIrManager getConsumerIrManager()
    {
        return (ConsumerIrManager) get(CONSUMER_IR_SERVICE);
    }

    public static MediaSessionManager getMediaSessionManager()
    {
        return (MediaSessionManager) get(MEDIA_SESSION_SERVICE);
    }

    public static BatteryManager getBatteryManager()
    {
        return (BatteryManager) get(BATTERY_SERVICE);
    }

    public static JobScheduler getJobScheduler()
    {
        return (JobScheduler) get(JOB_SCHEDULER_SERVICE);
    }

    public static MidiManager getMidiManager()
    {
        return (MidiManager) get(MIDI_SERVICE);
    }

    public static HardwarePropertiesManager getHardwarePropertiesManager()
    {
        return (HardwarePropertiesManager) get(HARDWARE_PROPERTIES_SERVICE);
    }
}
