package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayvytr.easyandroid.R;
import com.ayvytr.easyandroid.tools.BitmapTool;
import com.ayvytr.easyandroid.tools.Colors;
import com.ayvytr.easyandroid.tools.withcontext.DensityTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Do on 2017/5/31.
 */

public class QuickIndexView extends View
{
    private static final int DEFAULT_TEXT_SIZE_DP = 18;
    private static final int DEFAULT_QUICK_TEXT_SIZE_DP = 40;
    private static final int DEFAULT_WIDTH_DP = 50;

    private static final int NO_POSITION = -1;

    private Paint paint;
    private Paint toastPaint;

    private Toast toast;
    private boolean showToast;

    private Bitmap topBitmap;
    private Bitmap bottomBitmap;

    private List<String> letterList;
    private int textColor;
    private int textSize;
    private int quickTextColor;
    private int quickTextSize;
    private Context context;
    private OnLetterChangeListener onLetterChangeListener;

    private Rect bitmapRect;
    private Rect outRect;
    private TextView toastView;

    public QuickIndexView(Context context)
    {
        this(context, null);
    }

    public QuickIndexView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
        init(attrs);
    }

    private void init(AttributeSet attrs)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

        toastPaint = new Paint();
        toastPaint.setAntiAlias(true);
        toastPaint.setColor(quickTextColor);
        toastPaint.setTextSize(quickTextSize);

        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);

        toastView = new TextView(context);
        toastView.setGravity(Gravity.CENTER);
        toast.setView(toastView);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QuickIndexView);
        textColor = typedArray.getColor(R.styleable.QuickIndexView_textColor, Colors.BLACK);
        textSize = typedArray
                .getDimensionPixelSize(R.styleable.QuickIndexView_textSize, DensityTool.dp2px(DEFAULT_TEXT_SIZE_DP));
        quickTextColor = typedArray.getColor(R.styleable.QuickIndexView_quickTextColor, Colors.BLACK);
        quickTextSize = typedArray.getDimensionPixelSize(R.styleable.QuickIndexView_quickTextSize,
                DensityTool.dp2px(DEFAULT_QUICK_TEXT_SIZE_DP));
        Drawable topDrawable = typedArray.getDrawable(R.styleable.QuickIndexView_topDrawable);
        if(topDrawable != null)
        {
            topBitmap = BitmapTool.toBitmap(topDrawable);
        }
        Drawable bottomDrawable = typedArray.getDrawable(R.styleable.QuickIndexView_bottomDrawable);
        if(bottomDrawable != null)
        {
            bottomBitmap = BitmapTool.toBitmap(bottomDrawable);
        }

        showToast = typedArray.getBoolean(R.styleable.QuickIndexView_showToast, true);

        toastView.setTextColor(quickTextColor);
        toastView.setTextSize(quickTextSize);

        CharSequence[] textArray = typedArray.getTextArray(R.styleable.QuickIndexView_quickLetters);
        if(textArray != null)
        {
            letterList = new ArrayList<>();
            for(CharSequence charSequence : textArray)
            {
                letterList.add(charSequence.toString());
            }
        }

        typedArray.recycle();

        bitmapRect = new Rect();
        outRect = new Rect();
    }

    public QuickIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST)
        {
            width = DensityTool.dp2px(context, DEFAULT_WIDTH_DP);
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        paint.setColor(textColor);
        paint.setTextSize(textSize);

        int letterLength = getLetterLength();
        paint.setTextSize(letterLength);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int y = getPaddingTop();

        int x = getWidth() >> 1;
        int halfLetterLength = letterLength >> 1;

        if(topBitmap != null)
        {
            bitmapRect.bottom = topBitmap.getHeight();
            bitmapRect.right = topBitmap.getWidth();
            outRect.left = x - halfLetterLength;
            outRect.right = x + halfLetterLength;
            outRect.top = y;
            outRect.bottom = y + letterLength;
            canvas.drawBitmap(topBitmap, bitmapRect, outRect, null);
            y += letterLength;
        }

        int fontY = (int) (halfLetterLength - fontMetrics.top / 2 - fontMetrics.bottom / 2);
        int size = letterList.size();
        for(int i = 0; i < size; i++)
        {
            canvas.drawText(letterList.get(i), x, y + fontY, paint);
            y += letterLength;
        }

        if(bottomBitmap != null)
        {
            bitmapRect.bottom = bottomBitmap.getHeight();
            bitmapRect.right = bottomBitmap.getWidth();
            outRect.left = x - halfLetterLength;
            outRect.right = x + halfLetterLength;
            outRect.top = y;
            outRect.bottom = y + letterLength;
            canvas.drawBitmap(bottomBitmap, bitmapRect, outRect, null);
        }
    }

    /**
     * 返回实际绘制的每个Letter文字高度，因为动态设置时，如果设置的文字高度太大，绘制出来也没有意义.
     *
     * @return 实际每个Letter文字高度
     */
    private int getLetterLength()
    {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int letterCount = getLetterCount();

        return Math.min(width, (getHeight() - getPaddingTop() - getPaddingBottom()) / letterCount);
    }

    private int getLetterCount()
    {
        int itemCount = letterList.size();
        if(topBitmap != null)
        {
            itemCount++;
        }
        if(bottomBitmap != null)
        {
            itemCount++;
        }
        return itemCount;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //点击顶部或者底部Padding时，不响应事件
        if(event.getY() < getPaddingTop() || event.getY() > (getHeight() - getPaddingBottom()))
        {
            return true;
        }

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            {
                int letterCount = getLetterCount();
                int letterLength = getLetterLength();
                int index = 0;
                for(int i = 0; i < letterCount; i++)
                {
                    int y = getPaddingTop() + letterLength * i;
                    if(event.getY() > y && event.getY() < y + letterLength)
                    {
                        index = i;
                        break;
                    }
                }

                String letter;
                if(topBitmap != null)
                {
                    index--;
                }

                if(index == NO_POSITION)
                {
                    index = 0;
                    letter = "";

                    ImageView view = new ImageView(context);
                    view.setImageBitmap(topBitmap);
                    toast.setView(view);
                }
                else if(index >= letterList.size())
                {
                    index = letterList.size() - 1;
                    letter = "";
                    ImageView view = new ImageView(context);
                    view.setImageBitmap(bottomBitmap);
                    toast.setView(view);
                }
                else
                {
                    letter = letterList.get(index);
                    String text = letterList.get(topBitmap == null ? index : index - 1);
                    toastView.setText(text);
                    toast.setView(toastView);
                }

                if(showToast)
                {
                    toast.show();
                }

                if(onLetterChangeListener != null)
                {
                    onLetterChangeListener.onLetterChange(index, letter, this);
                }
            }
            break;
            case MotionEvent.ACTION_UP:
                if(showToast)
                {
                    toast.cancel();
                }
                break;
        }

        return true;
    }

    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener)
    {
        this.onLetterChangeListener = onLetterChangeListener;
    }

    /**
     * 字母索引变化监听器
     */
    public interface OnLetterChangeListener
    {
        /**
         * {@link #onTouchEvent(MotionEvent)} 触发时，调用此字母索引变化方法
         *
         * @param position       当前position.<br>如果指向 {@link #topBitmap}, {@code position=0}<br>
         *                       如果指向 {@link #bottomBitmap}, {@code position=} {@link #letterList} {@code .size()}.
         * @param text           当前指向的文本.<br>如果指向 {@link #topBitmap} 或者 {@link #bottomBitmap}，{@code text=""}.
         * @param quickIndexView {@link QuickIndexView}
         */
        void onLetterChange(int position, String text, QuickIndexView quickIndexView);
    }
}
