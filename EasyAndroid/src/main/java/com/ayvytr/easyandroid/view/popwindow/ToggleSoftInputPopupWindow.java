package com.ayvytr.easyandroid.view.popwindow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;

/**
 * 自定义PopupWindow，在PopupWindow和输入法同时打开时，确保输入法不会遮挡PopupWindow，提供了方法，能够弹出或关闭输入法.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.1
 */
public class ToggleSoftInputPopupWindow extends AlphaPopupWindow
{
    private InputMethodManager imm;

    public ToggleSoftInputPopupWindow(Activity activity, @LayoutRes int resource, @ColorInt int color,
                                      int animationStyle)
    {
        super(activity, resource, color, animationStyle);
        //设置需要输入法
        setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void show(boolean isCenter, boolean isLeft, boolean isTop, int x, int y)
    {
        super.show(isCenter, isLeft, isTop, x, y);
        switchShowInputMethod();
    }

    @Override
    public void show()
    {
        super.show();
        switchShowInputMethod();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y)
    {
        super.showAtLocation(parent, gravity, x, y);
        switchShowInputMethod();
    }

    @Override
    public void showAsFullScreen()
    {
        super.showAsFullScreen();
        switchShowInputMethod();
    }

    @Override
    public void showAsDropDown(View anchor)
    {
        super.showAsDropDown(anchor);
        switchShowInputMethod();
    }

    /**
     * 伴随窗口打开或关闭，输入法也随之打开或关闭（实际上作用不是特别重要。PopupWindow和输入法同时打开后，输入法窗口获取了焦点，
     * 这时候点击PopupWindow，关闭的是输入法，PopupWindow实际上还没有关闭【我用小米手机测试的】，有用的场景是PopupWindow打开时，
     * EditText等获取了焦点；或者PopupWindow的布局提供了关闭按钮，这时候PopupWindow和输入法会一起关闭）.
     */
    private void switchShowInputMethod()
    {
        if(!isShowing())
        {
            showInputMethod();
        }
        else
        {
            hideInputMethod();
        }
    }

    public void showInputMethod()
    {
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void hideInputMethod()
    {
        imm.hideSoftInputFromInputMethod(getContentView().getWindowToken(), 0);
    }
}
