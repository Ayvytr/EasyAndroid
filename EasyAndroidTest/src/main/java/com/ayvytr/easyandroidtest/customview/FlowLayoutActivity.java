package com.ayvytr.easyandroidtest.customview;

import android.os.Bundle;

import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroidtest.R;

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
    protected int getContentViewRes()
    {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
    }
}
