package com.ayvytr.easyandroidtest.stickyheader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ayvytr.easyandroidtest.R;

import butterknife.ButterKnife;

public class StickyHeaderActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header);
        ButterKnife.bind(this);
    }

    public void onBasic(View view)
    {
        startActivity(new Intent(this, BasicStickyHeaderActivity.class));
    }

    public void onCustom1(View view)
    {
        startActivity(new Intent(this, TestStickyActivity1.class));
    }
}
