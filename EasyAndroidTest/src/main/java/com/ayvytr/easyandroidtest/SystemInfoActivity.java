package com.ayvytr.easyandroidtest;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemInfoActivity extends AppCompatActivity
{
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        ButterKnife.bind(this);

        init();
    }

    private void init()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(Build.BOARD);
        sb.append("\n");
        sb.append(Build.BOOTLOADER);
        sb.append("\n");
        sb.append(Build.BRAND);
        tv.setText(sb.toString());
    }
}
