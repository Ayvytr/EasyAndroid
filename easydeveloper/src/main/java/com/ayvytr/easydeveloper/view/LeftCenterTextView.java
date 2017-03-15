package com.ayvytr.easydeveloper.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by Doing on 2017/3/5.
 */

public class LeftCenterTextView extends AppCompatTextView
{
    public LeftCenterTextView(Context context)
    {
        this(context, null);
    }

    public LeftCenterTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public LeftCenterTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER_VERTICAL);
    }
}
