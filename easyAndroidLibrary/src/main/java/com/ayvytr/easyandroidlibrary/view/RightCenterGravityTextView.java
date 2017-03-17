package com.ayvytr.easyandroidlibrary.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Right center gravity text TextView.
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.5.0
 */

public class RightCenterGravityTextView extends AppCompatTextView
{
    public RightCenterGravityTextView(Context context)
    {
        this(context, null);
    }

    public RightCenterGravityTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public RightCenterGravityTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
    }
}
