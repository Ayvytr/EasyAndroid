package com.ayvytr.easyandroidtest;

import android.os.Bundle;

import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroid.view.custom.AuthEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity extends BaseEasyActivity
{
    @BindView(R.id.authEditText)
    AuthEditText authEditText;

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
    }
}
