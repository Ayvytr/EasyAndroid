package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayvytr.easyandroid.R;
import com.ayvytr.easyandroid.tools.Colors;
import com.ayvytr.easyandroid.tools.Convert;
import com.ayvytr.easyandroid.tools.withcontext.DensityTool;
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

    private int bgStrokeWidth = 2;
    private ShapeDrawable drawable;
    private TextWatcher textWatcher;

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
//        inflate(context, R.layout.layout_auth_edit_text, this);
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

        etInput.setText("");

        initShapeDrawable();

        setTextViewBackground(drawable);
        setTextLength(DEFAULT_TEXT_LENGTH);
    }

    private void createView()
    {
//        etInput = (EditText) findViewById(R.id.etInput);
//        llTv = (LinearLayout) findViewById(R.id.llTv);
        etInput = new EditText(context);
        etInput.setCursorVisible(false);
        etInput.setBackgroundDrawable(null);

        llTv = new LinearLayout(context);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(etInput, lp);
        addView(llTv, lp);
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
    }

    private void initShapeDrawable()
    {
        drawable = new ShapeDrawable(new RectShape());
        Paint paint = drawable.getPaint();
        paint.setColor(textFrameColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DensityTool.dp2px(context, bgStrokeWidth));
    }

    public void setTextFrameColor(@ColorInt int color)
    {
        this.textFrameColor = color;
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
            list.remove(list.size() - 1);
        }

        while(list.size() < this.textLength)
        {
            list.add(getDefaultTextView());
        }

        reviseTextBorderLocation();

        llTv.removeAllViews();
        for(TextView tv : list)
        {
            llTv.addView(tv);
        }

        etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(textLength)});
        textWatcher = new TextWatcher()
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
                onTextChange(s.toString());
            }
        };
        etInput.removeTextChangedListener(textWatcher);
        etInput.addTextChangedListener(textWatcher);
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
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        int margin = -DensityTool.dp2px(context, bgStrokeWidth / 2);
        lp.rightMargin = margin;
        tv.setLayoutParams(lp);
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

    public int getDefaultWidth()
    {
        return ResCompat.getDimen(R.dimen._160dp);
    }

    public int getDefaultHeight()
    {
        return ResCompat.getDimen(R.dimen._60dp);
    }
}
