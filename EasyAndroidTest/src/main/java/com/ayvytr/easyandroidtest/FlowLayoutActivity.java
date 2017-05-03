package com.ayvytr.easyandroidtest;

import android.os.Bundle;

import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;

import butterknife.ButterKnife;

public class FlowLayoutActivity extends BaseEasyActivity
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
