package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
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

    // TextView 文本大小
    private int textSize;
    // 最大输入长度
    private int maxLength;

    // TextView 列表
    private ArrayList<TextView> textViewList;

    // TextView 字体颜色
    @ColorInt
    private int textColor;
    // TextView 边框颜色
    @ColorInt
    private int frameColor;

    // TextView 边框宽度
    private int frameWidth;

    //输入类型
    private InputType inputType;

    //密码显示字符串，输入类型为密码时，显示的字符串
    private String passwordString;

    //当前输入的字符串
    private String text = "";

    //输入变化监听器
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

    /**
     * 添加默认的文本监听器
     */
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

    /**
     * 文本变化处理
     */
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

    /**
     * 填充TextView文字
     */
    private void fillText()
    {
        for(int i = 0; i < maxLength; i++)
        {
            String s = i >= text.length() ? "" : (inputType.isPassword() ? passwordString : Convert
                    .toString(text.charAt(i)));
            textViewList.get(i).setText(s);
        }
    }

    /**
     * 设置最大长度，支持 {@link #MIN_LENGTH} ~ {@link #MAX_LENGTH}.
     *
     * @param maxLength 最大长度
     */
    public void setMaxLength(int maxLength)
    {
        if(maxLength < MIN_LENGTH || maxLength > MAX_LENGTH)
        {
            throw new RuntimeException(String.format("只支持长度为%d~%d的最大长度", MIN_LENGTH, MAX_LENGTH));
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

    /**
     * 设置 TextView文本颜色
     *
     * @param textColor 文本颜色
     */
    public void setTextColor(@ColorInt int textColor)
    {
        this.textColor = textColor;
        for(TextView tv : textViewList)
        {
            tv.setTextColor(this.textColor);
        }
    }

    /**
     * 设置 TextView 文本尺寸，像素
     *
     * @param textSize 文本尺寸
     */
    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
        for(TextView tv : textViewList)
        {
            tv.setTextSize(this.textSize);
        }
    }

    /**
     * 创建 TextView 并返回
     *
     * @return {@link TextView}
     */
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

    /**
     * 创建 TextView 背景 {@link android.graphics.drawable.Drawable}
     *
     * @return {@link ShapeDrawable}
     */
    private ShapeDrawable createFrameDrawableBg()
    {
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        Paint paint = shape.getPaint();
        paint.setColor(frameColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(frameWidth);
        return shape;
    }

    /**
     * 设置 TextView边框颜色
     *
     * @param frameColor 边框颜色
     */
    public void setFrameColor(@ColorInt int frameColor)
    {
        this.frameColor = frameColor;
        for(TextView tv : textViewList)
        {
            tv.setBackgroundDrawable(createFrameDrawableBg());
        }
    }

    /**
     * 设置 TextView边框宽度，单位为像素
     *
     * @param frameWidth 边框宽度
     */
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

    /**
     * 设置输入类型
     *
     * @param newInputType 新的输入类型，见{@link InputType}
     */
    public void setInputType(InputType newInputType)
    {
        if(this.inputType.needClearText(newInputType))
        {
            clearText();
        }
        this.inputType = newInputType;

        et.setInputType(inputType.toTextInputType());
        setPasswordString(passwordString);
    }

    /**
     * 清空当前文本
     */
    public void clearText()
    {
        this.text = "";
        et.setText(text);
        fillText();
    }

    /**
     * 设置密码字符串，在输入类型为密码时，在TextView上显示的文本.
     *
     * @param passwordString 密码字符串
     */
    public void setPasswordString(@Nullable String passwordString)
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

    /**
     * 获取 EditText
     *
     * @return {@link #et}
     */
    public EditText getEditText()
    {
        return et;
    }

    /**
     * 获取文本尺寸
     *
     * @return {@link #textSize}
     */
    public int getTextSize()
    {
        return textSize;
    }

    /**
     * 获取文本输入最大值
     *
     * @return {@link #maxLength}
     */
    public int getMaxLength()
    {
        return maxLength;
    }

    /**
     * 获取 TextView列表.
     *
     * @return {@link #textViewList}
     */
    public ArrayList<TextView> getTextViewList()
    {
        return textViewList;
    }

    /**
     * 获取 TextView 文本颜色.
     *
     * @return {@link #textColor}
     */
    public int getTextColor()
    {
        return textColor;
    }

    /**
     * 获取 TextView 边框颜色.
     *
     * @return {@link #frameColor}
     */
    public int getFrameColor()
    {
        return frameColor;
    }

    /**
     * 获取 TextView 边框宽度
     *
     * @return {@link #frameWidth}
     */
    public int getFrameWidth()
    {
        return frameWidth;
    }

    /**
     * 获取输入类型
     *
     * @return {@link #inputType}
     */
    public InputType getInputType()
    {
        return inputType;
    }

    /**
     * 获取密码字符串
     *
     * @return {@link #passwordString}
     */
    public String getPasswordString()
    {
        return passwordString;
    }

    /**
     * 获取当前输入的文本.
     *
     * @return {@link #text}
     */
    public String getText()
    {
        return text;
    }

    /**
     * 设置输入文本变化监听器
     *
     * @param onInputChangeListener {@link OnInputChangeListener}
     */
    public void setOnInputChangeListener(OnInputChangeListener onInputChangeListener)
    {
        this.onInputChangeListener = onInputChangeListener;
    }


    /**
     * 输入类型枚举类，通过设置 {@link #et} 输入类型，实现动态输入类型变化，在输入类型从大范围设置为小范围时，已输入的文本会自动
     * 清除.
     */
    public enum InputType
    {
        NUMBER,
        PASSWORD,
        VISIBLE_PASSWORD,
        NUMBER_PASSWORD,
        TEXT,
        TEXT_PASSWORD;

        /**
         * 根据 i 返回输入类型，读取自定义属性时用到，{@link #init(AttributeSet)}
         *
         * @param i 输入类型整形值
         * @return {@link InputType}
         */
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

        /**
         * 把输入类型转化为 {@link android.text.InputType}，默认输入类型为 {@link android.text.InputType#TYPE_CLASS_NUMBER}.
         *
         * @return 文本输入类型整形值
         */
        public int toTextInputType()
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

        /**
         * 判断输入类型是不是密码类型
         *
         * @return {@link true} 是密码类型.
         */
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

    /**
     * 文本变化监听器，提供给外部的监听器，可以在文本变化或者输入完成后自定义处理.
     */
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
