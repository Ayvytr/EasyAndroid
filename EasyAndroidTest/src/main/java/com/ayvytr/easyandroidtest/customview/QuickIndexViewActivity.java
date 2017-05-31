package com.ayvytr.easyandroidtest.customview;

import android.os.Bundle;

import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroidtest.R;

import butterknife.ButterKnife;

public class QuickIndexViewActivity extends BaseEasyActivity
{

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_quick_index_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
    }


}
