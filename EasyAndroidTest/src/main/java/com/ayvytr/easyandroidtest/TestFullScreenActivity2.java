package com.ayvytr.easyandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ayvytr.easyandroid.tools.withcontext.ScreenTool;

public class TestFullScreenActivity2 extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_full_screen2);
        setTitle("测试Activity");
    }

    public void onEnterFullScreen(View view)
    {
        ScreenTool.switchFullScreen(this, true);
    }

    public void onExitFullScreen(View view)
    {
        ScreenTool.switchFullScreen(this, false);
    }

    public void onEnterFullScreenWithActionBar(View view)
    {
        ScreenTool.switchFullScreenWithActionBar(this, true);
    }

    public void onExitFullScreenWithActionBar(View view)
    {
        ScreenTool.switchFullScreenWithActionBar(this, false);
    }
}
