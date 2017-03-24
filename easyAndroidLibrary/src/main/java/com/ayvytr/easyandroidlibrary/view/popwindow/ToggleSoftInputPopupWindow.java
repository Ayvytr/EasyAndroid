package com.ayvytr.easyandroidlibrary.view.popwindow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;

/**
 * 自定义PopupWindow，显示时自动弹出软键盘.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.1
 */
public class ToggleSoftInputPopupWindow extends AlphaPopupWindow
{
    public ToggleSoftInputPopupWindow(Activity activity, @LayoutRes int resource,
                                      @ColorInt int color,
                                      int animationStyle)
    {
        super(activity, resource, color, animationStyle);
        //设置需要输入法
        setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void show()
    {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if(!isShowing())
        {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        else
        {
            imm.hideSoftInputFromInputMethod(getContentView().getWindowToken(), 0);
        }
        super.show();
    }
}
