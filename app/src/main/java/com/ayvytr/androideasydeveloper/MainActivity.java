package com.ayvytr.androideasydeveloper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ayvytr.easydeveloper.Easy;
import com.ayvytr.easydeveloper.tools.withcontext.ClipboardTool;
import com.ayvytr.easydeveloper.tools.withcontext.ResTool;
import com.ayvytr.easydeveloper.tools.withcontext.ToastTool;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Easy.getDefault().init(getApplicationContext());
        init();
    }

    private void init()
    {
    }

    public void onGetClipboard(View view)
    {
        String text = ClipboardTool.getText("没获得");
        ToastTool.show(text);
    }

    public void onSetClipboard(View view)
    {
        ClipboardTool.setText("hello");
        ToastTool.show("已设置Hello到剪贴板");
    }

    public void onGetAppName(View view)
    {
        String string = ResTool.getString(R.string.app_name);
        ToastTool.show("获取到Res中APP名称:" + string);
    }
}
