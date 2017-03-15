package com.ayvytr.easyandroidlibrary.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by Doing on 2017/3/5.
 */

public class RightCenterTextView extends AppCompatTextView
{
    public RightCenterTextView(Context context)
    {
        this(context, null);
    }

    public RightCenterTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public RightCenterTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
    }
}
