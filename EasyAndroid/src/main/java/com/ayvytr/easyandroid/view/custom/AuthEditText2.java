package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayvytr.easyandroid.R;
import com.ayvytr.easyandroid.tools.Colors;
import com.ayvytr.easyandroid.tools.withcontext.DensityTool;

import java.util.ArrayList;

/**
 * Created by Do on 2017/6/8.
 */

public class AuthEditText2 extends RelativeLayout
{
    private static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 12;

    private Context context;

    private EditText et;
    private LinearLayout llTvContent;

    private int textSize;
    private int maxLength;

    private ArrayList<TextView> list;

    @ColorInt
    private int textColor;
    @ColorInt
    private int frameColor;

    private int frameWidth;

    private String input = "";

    private AuthEditText.AuthType inputType;

    private String passwordString;


    public AuthEditText2(Context context)
    {
        this(context, null);
    }

    public AuthEditText2(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public AuthEditText2(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs)
    {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.AuthEditText);

        textSize = t.getDimensionPixelSize(R.styleable.AuthEditText_textSize, 18);
        textColor = t.getColor(R.styleable.AuthEditText_textColor, 0xff888888);

        frameColor = t.getColor(R.styleable.AuthEditText_frameColor, Colors.BLACK);

        frameWidth = t.getDimensionPixelOffset(R.styleable.AuthEditText_frameWidth, 1);

        inputType = AuthEditText.AuthType.valueOf(t.getInt(R.styleable.AuthEditText_inputType, 0));

        passwordString = t.getString(R.styleable.AuthEditText_passwordString);

        list = new ArrayList<>(maxLength);
        et = new EditText(context);
        et.setBackgroundDrawable(null);
        et.setCursorVisible(false);

        llTvContent = new LinearLayout(context);
        addView(et, MATCH_PARENT, MATCH_PARENT);
        addView(llTvContent, MATCH_PARENT, MATCH_PARENT);

        int maxLength = t.getInt(R.styleable.AuthEditText_maxLength, 6);
        setMaxLength(maxLength);

        t.recycle();
    }

    public void setMaxLength(int maxLength)
    {
        if(maxLength < MIN_LENGTH || maxLength > MAX_LENGTH)
        {
            throw new RuntimeException("只支持长度为4-12的文本");
        }

        if(this.maxLength == maxLength)
        {
            return;
        }
        this.maxLength = maxLength;

        while(list.size() < maxLength)
        {
            TextView tv = createTextView();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            if(list.size() != 0)
            {
                lp.leftMargin = -DensityTool.dp2px(context, 1);
            }
            list.add(tv);
            llTvContent.addView(tv, lp);
        }

        while(list.size() > maxLength)
        {
            TextView tv = list.remove(list.size() - 1);
            llTvContent.removeView(tv);
        }
    }

    private TextView createTextView()
    {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(textColor);
        tv.setTextSize(textSize);
        tv.setBackgroundResource(R.drawable.auth_tv_bg);
        return tv;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }
}
