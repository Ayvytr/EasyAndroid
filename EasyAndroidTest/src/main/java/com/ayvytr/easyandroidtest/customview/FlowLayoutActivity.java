package com.ayvytr.easyandroidtest.customview;

import android.os.Bundle;

import com.ayvytr.easyandroid.view.activity.BaseActivity;
import com.ayvytr.easyandroidtest.R;

import butterknife.ButterKnife;

public class FlowLayoutActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
    }

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
    }
}
