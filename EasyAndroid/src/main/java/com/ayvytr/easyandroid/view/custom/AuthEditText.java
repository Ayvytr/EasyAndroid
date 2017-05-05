package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.ColorInt;
import android.support.annotation.Px;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayvytr.easyandroid.R;
import com.ayvytr.easyandroid.tools.Colors;
import com.ayvytr.easyandroid.tools.Convert;
import com.ayvytr.easyandroid.tools.withcontext.ResCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Date: 2017/5/5
 *
 * @author davidwang
 */

public class AuthEditText extends RelativeLayout
{
    private static final int DEFAULT_TEXT_LENGTH = 6;

    EditText etInput;
    LinearLayout llTv;

    private boolean isPassword = false;

    private int textLength = DEFAULT_TEXT_LENGTH;

    private List<TextView> list = new ArrayList<>(6);
    private Context context;

    private int textColor = 0xff888888;

    private int textFrameColor = Colors.BLACK;

    private int strokeWidth = 2;

    private ShapeDrawable drawable;

    private OnInputFinishedListener onInputFinishedListener;

    private int textSize = 18;
    private String string;

    public void setTextViewBackground(Drawable drawable)
    {
        this.tvBg = drawable;
    }

    private Drawable tvBg;

    public AuthEditText(Context context)
    {
        this(context, null);
    }

    public AuthEditText(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public AuthEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init()
    {
        createView();
        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etInput.requestFocus();
            }
        });

        etInput.setTextColor(Colors.TRANSPARENT);

        initShapeDrawable();

        setTextViewBackground(drawable);
        setTextLength(DEFAULT_TEXT_LENGTH);
    }

    private void createView()
    {
        etInput = new EditText(context);
        etInput.setCursorVisible(false);
        etInput.setBackgroundDrawable(null);
        TextWatcher textWatcher = new TextWatcher()
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
                string = s.toString();
                onTextChange(string);
            }
        };
        etInput.addTextChangedListener(textWatcher);

        llTv = new LinearLayout(context);

        addView(etInput);
        addView(llTv);
    }

    private void onTextChange(String s)
    {
        for(int i = 0; i < s.length(); i++)
        {
            list.get(i).setText(Convert.toString(s.charAt(i)));
        }

        for(int i = s.length(); i < textLength; i++)
        {
            list.get(i).setText("");
        }

        if(s.length() == textLength && onInputFinishedListener != null)
        {
            onInputFinishedListener.onFinish(this, s);
        }
    }

    private void initShapeDrawable()
    {
        drawable = new ShapeDrawable(new RectShape());
        Paint paint = drawable.getPaint();
        paint.setColor(textFrameColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
    }

    public void setFrameColor(@ColorInt int color)
    {
        this.textFrameColor = color;
        changeFrameColor();
    }

    private void changeFrameColor()
    {
        initShapeDrawable();
        for(TextView tv : list)
        {
            tv.setBackgroundDrawable(drawable);
        }
    }

    public void setTextColor(@ColorInt int color)
    {
        this.textColor = color;
        for(TextView tv : list)
        {
            tv.setTextColor(color);
        }
    }

    public void setTextSize(@Px int size)
    {
        if(size <= 0)
        {
            return;
        }

        this.textSize = size;
        for(TextView tv : list)
        {
            tv.setTextSize(textSize);
        }
    }

    public void setAuthType(AuthType authType)
    {
        int inputType = InputType.TYPE_NULL;
        switch(authType)
        {
            case NUMBER:
                inputType = InputType.TYPE_CLASS_NUMBER;
                break;
            case PASSWORD:
                inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD;
                break;
            case VISIBLE_PASSWORD:
                inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                break;
            case NUMBER_PASSWORD:
                inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD;
                break;
            case DEFAULT:
                inputType = InputType.TYPE_NULL;
                break;
        }
        etInput.setInputType(inputType);
        for(TextView tv : list)
        {
            tv.setInputType(inputType);
        }
    }


    public void setTextLength(int textLength)
    {
        if(textLength <= 0)
        {
            return;
        }

        this.textLength = textLength;

        while(list.size() > this.textLength)
        {
            TextView view = list.remove(list.size() - 1);
            llTv.removeView(view);
        }

        while(list.size() < this.textLength)
        {
            TextView view = getDefaultTextView();
            list.add(view);
            llTv.addView(view);
        }

        reviseTextBorderLocation();

        etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(textLength)});
    }

    private void reviseTextBorderLocation()
    {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) list.get(list.size() - 1)
                                                                       .getLayoutParams();
        lp.rightMargin = 0;
    }

    public TextView getDefaultTextView()
    {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(textSize);
        tv.setTextColor(textColor);
        tv.setBackgroundDrawable(tvBg);

        return tv;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int defaultWidth = getDefaultWidth();
        int defaultHeight = getDefaultHeight();
        if(widthMode == MeasureSpec.AT_MOST)
        {
            width = defaultWidth;
        }

        if(heightMode == MeasureSpec.AT_MOST)
        {
            height = defaultHeight;
        }

        if(width < defaultWidth)
        {
            width = defaultWidth;
        }

        if(height < defaultHeight)
        {
            height = defaultHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        LayoutParams lp = (LayoutParams) etInput.getLayoutParams();
        lp.width = w;
        lp.height = h;
        llTv.setLayoutParams(lp);

        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) list.get(0).getLayoutParams();
        int margin =  strokeWidth / 2;
        llp.leftMargin = margin;
        llp.rightMargin = 0;
        llp.width = 0;
        llp.height = h;
        llp.weight = 1;
        for(int i = 1; i < list.size(); i++)
        {
            TextView tv = list.get(i);
            tv.setLayoutParams(llp);
            tv.setBackgroundDrawable(drawable);
        }

        llp = (LinearLayout.LayoutParams) list.get(0).getLayoutParams();
        llp.leftMargin = 0;

        llTv.invalidate();
    }

    public int getDefaultWidth()
    {
        return ResCompat.getDimen(R.dimen._240dp);
    }

    public int getDefaultHeight()
    {
        return ResCompat.getDimen(R.dimen._60dp);
    }

    public void setOnInputFinishedListener(OnInputFinishedListener l)
    {
        onInputFinishedListener = l;
    }

    public void clearText()
    {
        etInput.setText("");
        for(TextView tv : list)
        {
            tv.setText("");
        }
    }

    public interface OnInputFinishedListener
    {
        void onFinish(AuthEditText authEditText, String s);
    }

    public enum AuthType
    {
        NUMBER,
        PASSWORD,
        VISIBLE_PASSWORD,
        NUMBER_PASSWORD,
        DEFAULT,
    }
}
