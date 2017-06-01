package com.ayvytr.easyandroidtest.customview;

import android.os.Bundle;

import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroid.view.custom.QuickIndexView;
import com.ayvytr.easyandroidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuickIndexViewActivity extends BaseEasyActivity
{

    @BindView(R.id.quickIndexView)
    QuickIndexView quickIndexView;

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_quick_index_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
        quickIndexView.setOnLetterChangeListener(new QuickIndexView.OnLetterChangeListener()
        {
            @Override
            public void onLetterChange(int position, String text, QuickIndexView quickIndexView)
            {

            }
        });
    }

}
