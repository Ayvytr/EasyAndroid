package com.ayvytr.easyandroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ayvytr.easyandroid.tools.withcontext.ScreenTool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestFullScreenActivity extends AppCompatActivity
{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_full_screen);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        setSupportActionBar(toolbar);
        setTitle("测试AppCompatActivity");
        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        toolbar.setTitle(R.string.this_is_appcompatactivity);
    }

    public void onExitFullScreen(View view)
    {
        ScreenTool.switchFullScreen(this, false);
    }

    public void onEnterFullScreen(View view)
    {
        ScreenTool.switchFullScreen(this, true);
    }

    public void onTestActivityFullScreen(View view)
    {
        startActivity(new Intent(this, TestFullScreenActivity2.class));
    }

    public void onExitFullScreenWithActionBar(View view)
    {
        ScreenTool.switchFullScreenWithActionBar(this, false);
    }

    public void onEnterFullScreenWithActionBar(View view)
    {
        ScreenTool.switchFullScreenWithActionBar(this, true);
    }
}
