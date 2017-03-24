package com.ayvytr.easyandroidlibrary.view.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Center gravity text TextView.
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.5.0
 */

public class CenterGravityTextView extends AppCompatTextView
{
    public CenterGravityTextView(Context context)
    {
        this(context, null);
    }

    public CenterGravityTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public CenterGravityTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
    }
}
