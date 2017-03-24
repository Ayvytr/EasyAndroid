package com.ayvytr.easyandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ayvytr.easyandroidlibrary.Easy;
import com.ayvytr.easyandroidlibrary.tools.withcontext.DensityTool;
import com.ayvytr.easyandroidlibrary.tools.withcontext.ResTool;
import com.ayvytr.easyandroidlibrary.tools.withcontext.ToastTool;
import com.ayvytr.easyandroidlibrary.view.popwindow.AlphaPopupWindow;
import com.ayvytr.easyandroidlibrary.view.popwindow.TopPopupWindow;

public class PopupWindowActivity extends AppCompatActivity
{

    private AlphaPopupWindow alphaPopupWindow;
    private TopPopupWindow topPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initView();
    }

    private void initView()
    {
        Easy.getDefault().init(this);
        alphaPopupWindow = new AlphaPopupWindow(this, R.layout.pw_main,
                getResources().getColor(R.color.colorPrimary),
                R.style.defPwAnimStyle);
        alphaPopupWindow.getContentView().findViewById(R.id.btnPopupWindow).setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        ToastTool.showLong("popupWindow显示啦！");
                    }
                });
        topPopupWindow = new TopPopupWindow(this, R.layout.pw_main, ResTool.getColor(R.color.colorPrimary),
                R.style.defPwAnimStyle);
        topPopupWindow.setWidth(DensityTool.dp2px(100));
        topPopupWindow.getContentView().findViewById(R.id.btnPopupWindow).setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        ToastTool.showLong("popupWindow显示啦！");
                    }
                });
    }

    public void onClickShowPopupWindow(View view)
    {
        alphaPopupWindow.show();
    }

    public void onClickShowTopPopupWindow(View view)
    {
        topPopupWindow.showTopRight();
    }
}
