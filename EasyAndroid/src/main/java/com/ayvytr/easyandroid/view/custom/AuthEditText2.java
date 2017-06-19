package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayvytr.easyandroid.R;
import com.ayvytr.easyandroid.tools.Colors;
import com.ayvytr.easyandroid.tools.Convert;

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

    private ShapeDrawable frameDrawableBg;

    private int frameWidth;

    private String input = "";

    private AuthEditText.AuthType inputType;

    private String passwordString;
    private String string = "";


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
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.AuthEditText2);

        textSize = t.getDimensionPixelSize(R.styleable.AuthEditText2_textSize, 18);
        textColor = t.getColor(R.styleable.AuthEditText2_textColor, 0xff888888);

        frameColor = t.getColor(R.styleable.AuthEditText2_frameColor, Colors.BLACK);

        frameWidth = t.getDimensionPixelOffset(R.styleable.AuthEditText2_frameWidth, 1);

        inputType = AuthEditText.AuthType.valueOf(t.getInt(R.styleable.AuthEditText2_inputType, 0));

        passwordString = t.getString(R.styleable.AuthEditText2_passwordString);

        list = new ArrayList<>(maxLength);
        et = new EditText(context);
        et.setBackgroundDrawable(null);
        et.setCursorVisible(false);
        et.setTextColor(Colors.TRANSPARENT);
        addDefaultTextChangeListener();

        llTvContent = new LinearLayout(context);
        addView(et, MATCH_PARENT, MATCH_PARENT);
        addView(llTvContent, MATCH_PARENT, MATCH_PARENT);

        int maxLength = t.getInt(R.styleable.AuthEditText2_maxLength, 6);
        setMaxLength(maxLength);

        t.recycle();
    }

    private void addDefaultTextChangeListener()
    {
        et.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                onTextChange(s);
            }
        });
    }

    private void onTextChange(Editable s)
    {
        string = s.toString();
        for(int i = 0; i < s.length() && i < maxLength; i++)
        {
            fillTextByIndex(i);
        }

        for(int i = s.length(); i < maxLength; i++)
        {
            list.get(i).setText("");
        }

        //        if(onInputFinishedListener != null)
        //        {
        //            if(s.length() == textLength)
        //            {
        //                onInputFinishedListener.onFinish(this, string);
        //            }
        //
        //            onInputFinishedListener.onTextChanged(string.length() == textLength, string);
        //        }
    }

    private void fillTextByIndex(int i)
    {
        list.get(i).setText(Convert.toString(string.charAt(i)));
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
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.maxLength)});
        if(string.length() > maxLength)
        {
            string = string.substring(0, maxLength);
        }

        while(list.size() < maxLength)
        {
            TextView tv = createTextView();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            if(list.size() != 0)
            {
                lp.leftMargin = -frameWidth;
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

    public void setTextColor(int textColor)
    {
        this.textColor = textColor;
        for(TextView tv : list)
        {
            tv.setTextColor(this.textColor);
        }
    }

    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
        for(TextView tv : list)
        {
            tv.setTextSize(this.textSize);
        }
    }

    private TextView createTextView()
    {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(textColor);
        tv.setTextSize(textSize);
        tv.setBackgroundDrawable(createFrameDrawableBg());
        return tv;
    }

    private ShapeDrawable createFrameDrawableBg()
    {
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        Paint paint = shape.getPaint();
        paint.setColor(frameColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(frameWidth);
        return shape;
    }

    public void setFrameColor(int frameColor)
    {
        this.frameColor = frameColor;
        for(TextView tv : list)
        {
            tv.setBackgroundDrawable(createFrameDrawableBg());
        }
    }

    public void setFrameWidth(int frameWidth)
    {
        this.frameWidth = frameWidth;
        for(int i = 1; i < list.size(); i++)
        {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) list.get(i).getLayoutParams();
            lp.leftMargin = -frameWidth;
        }
        for(TextView tv : list)
        {
            tv.setBackgroundDrawable(createFrameDrawableBg());
        }
    }

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
