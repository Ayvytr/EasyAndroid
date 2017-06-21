package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Px;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
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
 * 输入验证控件，类似淘宝支付时输入密码的控件，提供了多种方法，可以进行高度的自定义.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.8.1
 * @deprecated 从 1.8.5开始弃用，使用 {@link NewAuthEditText} 代替.
 */

@Deprecated
public class AuthEditText extends RelativeLayout
{
    public static final int DEFAULT_TEXT_LENGTH = 6;
    public static final int MIN_TEXT_LENGTH = 4;

    EditText etInput;
    LinearLayout llTv;

    //文本输入长度，决定了可以输入多少字符
    private int textLength;

    //集合，存储TextView
    private List<TextView> list = new ArrayList<>(6);

    private Context context;

    //TextView文本颜色
    private int textColor = 0xff888888;

    //边框颜色，在使用defaultDrawable时有用
    private int frameColor = Colors.BLACK;
    //默认文本边框(矩形)
    private ShapeDrawable defaultDrawable;

    //默认边框宽度，在使用defaultDrawable时有用
    private int strokeWidth = 2;

    //边框Drawable
    private Drawable frameDrawable;

    //当输入文本达到最大值的监听器
    private OnInputFinishedListener onInputFinishedListener;

    //默认字体大小
    private int textSize = 18;

    //接收输入的字符串
    private String string = "";

    /**
     * 默认的LayoutParams，是 {@link #list} 中TextView使用的
     */
    private LinearLayout.LayoutParams defaultLp;

    /**
     * 验证类型， {@link AuthType}
     */
    private AuthType authType = AuthType.NONE;

    //当输入类型为密码时，TextView显示的字符串.
    private String passwordStr = "●";

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

    /**
     * 获得 {@link #passwordStr}
     */
    public String getPasswordStr()
    {
        return passwordStr;
    }

    /**
     * 设置 {@link #passwordStr},当输入类型为密码时，显示这个字符串
     */
    public void setPasswordStr(String passwordStr)
    {
        this.passwordStr = passwordStr;
    }

    private void init()
    {
        initDefaultLp();
        initDefaultDrawable();
        initView();
        setTextLength(DEFAULT_TEXT_LENGTH);
        setAuthType(AuthType.NUMBER);

        addView(etInput);
        addView(llTv);
    }

    /**
     * 获取TextView集合，{@link #list}
     */
    public List<TextView> getTextViewList()
    {
        return list;
    }

    /**
     * 获取EditText，{@link #etInput}
     */
    public EditText getEditText()
    {
        return etInput;
    }

    /**
     * 获得焦点（比如控件被点击时）.
     */
    public void requestEditTextFocus()
    {
        etInput.requestFocus();
    }

    /**
     * 初始化 {@link #defaultLp}
     */
    private void initDefaultLp()
    {
        defaultLp = new LinearLayout.LayoutParams(0, getMeasuredHeight(), 1);
        defaultLp.leftMargin = getLeftMargin();
    }

    /**
     * 初始化View
     */
    private void initView()
    {
        initEditText();

        llTv = new LinearLayout(context);
    }

    /**
     * 获取验证类型
     */
    public AuthType getAuthType()
    {
        return authType;
    }

    /**
     * 初始化 {@link #etInput}
     */
    private void initEditText()
    {
        etInput = new EditText(context);
        etInput.setCursorVisible(false);
        etInput.setBackgroundDrawable(null);
        etInput.setTextColor(Colors.TRANSPARENT);
        setEditTextLength(textLength);

        addDefaultTextChangeListener();
    }

    /**
     * 为 {@link #etInput} 添加默认的文本变化监听器
     */
    private void addDefaultTextChangeListener()
    {
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
                onTextChange(s);
            }
        };
        etInput.addTextChangedListener(textWatcher);
    }

    /**
     * 默认的文本变化监听器文本变化时调用此方法，{@link #addDefaultTextChangeListener()}
     */
    private void onTextChange(Editable s)
    {
        string = s.toString();
        for(int i = 0; i < s.length(); i++)
        {
            fillTextByIndex(i);
        }

        for(int i = s.length(); i < textLength; i++)
        {
            list.get(i).setText("");
        }

        if(onInputFinishedListener != null)
        {
            if(s.length() == textLength)
            {
                onInputFinishedListener.onFinish(this, string);
            }

            onInputFinishedListener.onTextChanged(string.length() == textLength, string);
        }
    }

    /**
     * 设置 TextView的 Drawable
     *
     * @param drawable Drawable
     */
    public void setTextViewDrawable(Drawable drawable)
    {
        this.frameDrawable = drawable;

        changeTextViewDrawable();
    }

    /**
     * 修改 TextView 背景
     */
    private void changeTextViewDrawable()
    {
        for(TextView tv : list)
        {
            tv.setBackgroundDrawable(frameDrawable);
        }
    }

    /**
     * 设置 TextView的 Drawable
     *
     * @param id Drawable id
     */
    public void setTextViewDrawableRes(@DrawableRes int id)
    {
        this.frameDrawable = ResCompat.getDrawable(context, id);

        changeTextViewDrawable();
    }

    /**
     * 初始化默认的Drawable，{@link #defaultDrawable}
     */
    private void initDefaultDrawable()
    {
        defaultDrawable = new ShapeDrawable(new RectShape());
        Paint paint = defaultDrawable.getPaint();
        paint.setColor(frameColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        setTextViewDrawable(defaultDrawable);
    }

    /**
     * 设置 {@link #defaultDrawable} 的颜色
     */
    public void setFrameColor(@ColorInt int color)
    {
        this.frameColor = color;
        changeFrameColor();
    }

    /**
     * 更改 {@link #defaultDrawable} 的颜色
     */
    private void changeFrameColor()
    {
        initDefaultDrawable();
        changeTextViewDrawable();
    }

    /**
     * 设置文本颜色
     */
    public void setTextColor(@ColorInt int color)
    {
        this.textColor = color;
        for(TextView tv : list)
        {
            tv.setTextColor(color);
        }
    }

    /**
     * 设置文本大小
     */
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

    /**
     * 设置验证类型
     *
     * @param authType {@link AuthType}
     */
    public void setAuthType(AuthType authType)
    {
        if(this.authType == authType)
        {
            return;
        }

        /**
         * 在 {@link #authType} 值被改变之前，判断是不是需要清空文本.
         */
        boolean needClearText = needClearText(authType);

        this.authType = authType;

        int inputType = InputType.TYPE_CLASS_NUMBER;
        switch(this.authType)
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
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                {
                    inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD;
                }
                break;
            case DEFAULT:
                /**
                 * 这里的 InputType 不设置为 {@link InputType.TYPE_NULL},是因为设置这个后，
                 * {@link AuthEditText} 的点击事件失效
                 */
                inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
                break;
        }
        etInput.setInputType(inputType);

        if(needClearText)
        {
            clearText();
        }

        /**
         * 输入类型变化时，需要手动更改文本。直接调用 {@link TextView#setInputType(int)} 没有作用.
         */
        for(int i = 0; i < list.size(); i++)
        {
            if(string.length() > i)
            {
                fillTextByIndex(i);
            }
        }
    }

    /**
     * 判断是否需要清空文本，在 {@link #setAuthType(AuthType)} 时使用
     *
     * @param authType 将要设置的 {@link AuthType}
     */
    private boolean needClearText(AuthType authType)
    {
        if((authType == AuthType.NUMBER || authType == AuthType.NUMBER_PASSWORD) &&
                this.authType != AuthType.NUMBER && this.authType != AuthType.NUMBER_PASSWORD)
        {
            return true;
        }

        return false;
    }

    /**
     * 根据 TextView的索引，填充文本.
     *
     * @param i 索引
     */
    private void fillTextByIndex(int i)
    {
        list.get(i).setText(isVariantPassword() ? passwordStr : Convert.toString(string.charAt(i)));
    }


    /**
     * 判断返回当前输入类型是不是密码.
     */
    private boolean isVariantPassword()
    {
        return authType == AuthType.PASSWORD || authType == AuthType.NUMBER_PASSWORD;
    }

    /**
     * 设置文本输入长度
     */
    public void setTextLength(int textLength)
    {
        if(textLength < MIN_TEXT_LENGTH || this.textLength == textLength)
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
            llTv.addView(view, defaultLp);
        }

        if(string.length() > textLength)
        {
            clearText();
        }

        setEditTextLength(this.textLength);
    }

    /**
     * 设置 {@link #etInput} 最大输入长度.
     *
     * @param textLength 文本输入长度
     */
    private void setEditTextLength(int textLength)
    {
        etInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(textLength)});
    }

    /**
     * 获取返回默认的 TextView
     */
    public TextView getDefaultTextView()
    {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(textSize);
        tv.setTextColor(textColor);
        tv.setBackgroundDrawable(frameDrawable);

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

        //限制最小宽高值
        if(width < defaultWidth)
        {
            width = defaultWidth;
        }
        if(height < defaultHeight)
        {
            height = defaultHeight;
        }

        /**
         * 这里测量只能使用 LayoutParams, 通过 {@link android.view.View.MeasureSpec.makeMeasureSpec(int,int)}
         * 貌似没有用
         */
        LayoutParams lp = (LayoutParams) etInput.getLayoutParams();
        lp.width = width;
        lp.height = height;
        llTv.setLayoutParams(lp);

        LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) list.get(1).getLayoutParams();
        llp.leftMargin = getLeftMargin();
        llp.width = 0;
        llp.height = height;
        llp.weight = 1;
        for(int i = 1; i < list.size(); i++)
        {
            TextView tv = list.get(i);
            tv.setLayoutParams(llp);
        }

        llp = (LinearLayout.LayoutParams) list.get(1).getLayoutParams();
        llp.leftMargin = 0;
        list.get(0).setLayoutParams(llp);

        setMeasuredDimension(width, height);
    }

    /**
     * 获取 TextView向左偏移尺寸
     */
    private int getLeftMargin()
    {
        return -strokeWidth / 2;
    }

    public int getDefaultWidth()
    {
        return ResCompat.getDimen(R.dimen._160dp);
    }

    public int getDefaultHeight()
    {
        return ResCompat.getDimen(R.dimen._30dp);
    }

    /**
     * 设置输入完成时的监听
     *
     * @param l 监听器 {@link OnInputFinishedListener}
     */
    public void setOnInputFinishedListener(OnInputFinishedListener l)
    {
        onInputFinishedListener = l;
    }

    /**
     * 清除输入的所有文本
     */
    public void clearText()
    {
        string = "";
        etInput.setText("");
        for(TextView tv : list)
        {
            tv.setText("");
        }
    }

    /**
     * 获取当前文本长度
     *
     * @return {@link #textLength}
     */
    public int getTextLength()
    {
        return textLength;
    }

    /**
     * 获取文本颜色
     *
     * @return {@link #textColor}
     */
    public int getTextColor()
    {
        return textColor;
    }

    /**
     * 获取文本边框颜色
     *
     * @return {@link #frameColor}
     */
    public int getFrameColor()
    {
        return frameColor;
    }

    /**
     * 获取默认 ShapeDrawable.
     *
     * @return {@link #defaultDrawable}
     */
    public ShapeDrawable getDefaultDrawable()
    {
        return defaultDrawable;
    }

    /**
     * 获取文本边框宽度px.
     *
     * @return {@link #strokeWidth}
     */
    public int getStrokeWidth()
    {
        return strokeWidth;
    }

    /**
     * 设置文本边框宽度px
     *
     * @param strokeWidth 要设置的文本宽度
     */
    public void setStrokeWidth(int strokeWidth)
    {
        if(strokeWidth > 0)
        {
            this.strokeWidth = strokeWidth;
        }
    }

    /**
     * 获取文本边框Drawable
     *
     * @return {@link #frameDrawable {@link #frameDrawable {@link #frameDrawable {@link #frameDrawable}}}}
     */
    public Drawable getFrameDrawable()
    {
        return frameDrawable;
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
     * 获取当前输入的文本
     *
     * @return {@link #string}
     */
    public String getString()
    {
        return string;
    }


    /**
     * 输入文本到最大长度时触发的接口
     */
    public interface OnInputFinishedListener
    {
        /**
         * 当输入完成时
         *
         * @param authEditText {@link AuthEditText}
         * @param s            输入的字符串 {@link #string}
         */
        void onFinish(AuthEditText authEditText, String s);

        /**
         * 当文本变化时会回调此方法（在切换验证模式时，从密码到数字会清空文本。关于这个的判断暂时不加）.
         *
         * @param isFinished 文本输入是否完成.
         * @param s          当前已输入的字符串.
         */
        void onTextChanged(boolean isFinished, String s);
    }

    //验证类型枚举类
    public enum AuthType
    {
        NUMBER,
        PASSWORD,
        VISIBLE_PASSWORD,
        NUMBER_PASSWORD,
        DEFAULT,
        //这个没有实际作用
        NONE,;

        public static AuthType valueOf(int i)
        {
            switch(i)
            {
                case 1:
                    return NUMBER;
                case 2:
                    return PASSWORD;
                case 3:
                    return VISIBLE_PASSWORD;
                case 4:
                    return NUMBER_PASSWORD;
                default:
                    return DEFAULT;
            }
        }
    }
}
