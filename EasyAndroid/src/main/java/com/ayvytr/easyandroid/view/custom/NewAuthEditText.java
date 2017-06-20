package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
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
 * 输入验证控件，类似淘宝支付时输入密码的控件，提供了多种方法，可以进行高度的自定义，主要用来替换 {@link AuthEditText}，因为
 * {@link AuthEditText} 存在比较多问题，最主要问题是实际使用时，布局中预览不正常；没有自定义属性，定制性不强；重写
 * {@link ViewGroup#onMeasure(int, int)} 等方法时，做了多余的干预等问题，所以最好使用{@link NewAuthEditText}来替代.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.8.5
 */

public class NewAuthEditText extends RelativeLayout
{
    private static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 12;
    private static final int DEFAULT_MAX_LENGTH = 6;

    private Context context;

    private EditText et;
    private LinearLayout llTvContent;

    private int textSize;
    private int maxLength;

    private ArrayList<TextView> textViewList;

    @ColorInt
    private int textColor;
    @ColorInt
    private int frameColor;

    private int frameWidth;

    private InputType inputType;

    private String passwordString;

    private String text = "";

    private OnInputChangeListener onInputChangeListener;

    public NewAuthEditText(Context context)
    {
        this(context, null);
    }

    public NewAuthEditText(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public NewAuthEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs)
    {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.NewAuthEditText);

        textSize = t.getDimensionPixelSize(R.styleable.NewAuthEditText_textSize, 18);
        textColor = t.getColor(R.styleable.NewAuthEditText_textColor, 0xff888888);

        frameColor = t.getColor(R.styleable.NewAuthEditText_frameColor, Colors.BLACK);

        frameWidth = t.getDimensionPixelOffset(R.styleable.NewAuthEditText_frameWidth, 1);

        textViewList = new ArrayList<>(maxLength);

        passwordString = t.getString(R.styleable.NewAuthEditText_passwordString);
        setPasswordString(null);

        et = new EditText(context);
        et.setBackgroundDrawable(null);
        et.setCursorVisible(false);
        et.setTextColor(Colors.TRANSPARENT);
        addDefaultTextChangeListener();

        inputType = InputType.valueOf(t.getInt(R.styleable.NewAuthEditText_inputType, 0));
        setInputType(inputType);

        llTvContent = new LinearLayout(context);
        addView(et, MATCH_PARENT, MATCH_PARENT);
        addView(llTvContent, MATCH_PARENT, MATCH_PARENT);

        int maxLength = t.getInt(R.styleable.NewAuthEditText_maxLength, 6);
        //初始化时，需要做限制
        if(maxLength < MIN_LENGTH || maxLength > MAX_LENGTH)
        {
            maxLength = DEFAULT_MAX_LENGTH;
        }
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
        text = s.toString();
        fillText();

        if(onInputChangeListener != null)
        {
            if(s.length() == maxLength)
            {
                onInputChangeListener.onFinished(this, text);
            }

            onInputChangeListener.onTextChanged(text.length() == maxLength, text);
        }
    }

    private void fillText()
    {
        for(int i = 0; i < maxLength; i++)
        {
            String s = i >= text.length() ? "" : (inputType.isPassword() ? passwordString : Convert
                    .toString(text.charAt(i)));
            textViewList.get(i).setText(s);
        }
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
        if(text.length() > maxLength)
        {
            text = text.substring(0, maxLength);
        }

        while(textViewList.size() < maxLength)
        {
            TextView tv = createTextView();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            if(textViewList.size() != 0)
            {
                lp.leftMargin = -frameWidth;
            }
            textViewList.add(tv);
            llTvContent.addView(tv, lp);
        }

        while(textViewList.size() > maxLength)
        {
            TextView tv = textViewList.remove(textViewList.size() - 1);
            llTvContent.removeView(tv);
        }
    }

    public void setTextColor(int textColor)
    {
        this.textColor = textColor;
        for(TextView tv : textViewList)
        {
            tv.setTextColor(this.textColor);
        }
    }

    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
        for(TextView tv : textViewList)
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
        tv.setMaxLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);
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
        for(TextView tv : textViewList)
        {
            tv.setBackgroundDrawable(createFrameDrawableBg());
        }
    }

    public void setFrameWidth(int frameWidth)
    {
        this.frameWidth = frameWidth;
        for(int i = 1; i < textViewList.size(); i++)
        {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textViewList.get(i).getLayoutParams();
            lp.leftMargin = -frameWidth;
        }
        for(TextView tv : textViewList)
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

    public void setInputType(InputType newInputType)
    {
        if(this.inputType.needClearText(newInputType))
        {
            clearText();
        }
        this.inputType = newInputType;

        et.setInputType(inputType.toInputType());
        setPasswordString(passwordString);
    }

    private void clearText()
    {
        this.text = "";
        et.setText(text);
        fillText();
    }

    public void setPasswordString(String passwordString)
    {
        if(TextUtils.isEmpty(passwordString))
        {
            this.passwordString = "●";
        }
        else
        {
            this.passwordString = passwordString;
        }

        fillText();
    }

    public EditText getEditText()
    {
        return et;
    }

    public int getTextSize()
    {
        return textSize;
    }

    public int getMaxLength()
    {
        return maxLength;
    }

    public ArrayList<TextView> getTextViewList()
    {
        return textViewList;
    }

    public int getTextColor()
    {
        return textColor;
    }

    public int getFrameColor()
    {
        return frameColor;
    }

    public int getFrameWidth()
    {
        return frameWidth;
    }

    public InputType getInputType()
    {
        return inputType;
    }

    public String getPasswordString()
    {
        return passwordString;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public OnInputChangeListener getOnInputChangeListener()
    {
        return onInputChangeListener;
    }

    public void setOnInputChangeListener(OnInputChangeListener onInputChangeListener)
    {
        this.onInputChangeListener = onInputChangeListener;
    }

    public enum InputType
    {
        NUMBER,
        PASSWORD,
        VISIBLE_PASSWORD,
        NUMBER_PASSWORD,
        TEXT,
        TEXT_PASSWORD;

        public static InputType valueOf(int i)
        {
            switch(i)
            {
                case 0:
                    return NUMBER;
                case 1:
                    return PASSWORD;
                case 2:
                    return VISIBLE_PASSWORD;
                case 3:
                    return NUMBER_PASSWORD;
                case 4:
                    return TEXT;
                case 5:
                    return TEXT_PASSWORD;
                default:
                    return NUMBER;
            }
        }

        public int toInputType()
        {
            switch(this)
            {
                case NUMBER:
                    return android.text.InputType.TYPE_CLASS_NUMBER;
                case PASSWORD:
                    return android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
                case VISIBLE_PASSWORD:
                    return android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                case NUMBER_PASSWORD:
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    {
                        return android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD;
                    }
                    break;
                case TEXT:
                case TEXT_PASSWORD:
                    /**
                     * 这里的 InputType 不设置为 {@link android.text.InputType.TYPE_NULL},是因为设置这个后，
                     * {@link AuthEditText} 的点击事件失效
                     */
                    return android.text.InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
            }

            return android.text.InputType.TYPE_CLASS_NUMBER;
        }

        public boolean isPassword()
        {
            switch(this)
            {
                case PASSWORD:
                case NUMBER_PASSWORD:
                    return true;
                default:
                    return false;
            }
        }

        /**
         * 是否需要清空文本（当前所做判断可能不严谨，可以调用 {@link #clearText()}） 直接清空文本
         * 当前为文本，输入类型为数字或密码
         * 当前为密码，输入类型为数字
         *
         * @param newInputType 新的输入类型
         * @return {@code true }需要清空文本
         */
        public boolean needClearText(InputType newInputType)
        {
            if((this == TEXT || this == TEXT_PASSWORD) && newInputType != TEXT && newInputType != TEXT_PASSWORD)
            {
                return true;
            }

            if((this == PASSWORD || this == VISIBLE_PASSWORD) && this != newInputType)
            {
                return true;
            }

            return false;
        }
    }

    public interface OnInputChangeListener
    {
        /**
         * 当输入完成时
         *
         * @param authEditText {@link NewAuthEditText}
         * @param s            输入的字符串 {@link #text}
         */
        void onFinished(NewAuthEditText authEditText, String s);

        /**
         * 当文本变化时会回调此方法（在切换验证模式时，从密码到数字会清空文本。关于这个的判断暂时不加）.
         *
         * @param isFinished 文本输入是否完成.
         * @param s          当前已输入的字符串.
         */
        void onTextChanged(boolean isFinished, String s);
    }
}
