package com.ayvytr.easyandroid.view.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
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
    private static final int DEFAULT_TEXT_SIZE = 18;
    private static final int DEFAULT_QUICK_TEXT_SIZE = 40;
    private static final int DEFAULT_SPACE = 50;

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

    public QuickIndexView(Context context)
    {
        this(context, null);
    }

    public QuickIndexView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
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
        CenterGravityTextView view = new CenterGravityTextView(context);
        view.setText("A");
        view.setTextColor(Colors.BLACK);
        view.setTextSize(50);
        toast.setView(view);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QuickIndexView);
        textColor = typedArray.getColor(R.styleable.QuickIndexView_textColor, Colors.BLACK);
        textSize = (int) typedArray
                .getDimension(R.styleable.QuickIndexView_textSize, DensityTool.dp2px(DEFAULT_TEXT_SIZE));
        quickTextColor = typedArray.getColor(R.styleable.QuickIndexView_quickTextColor, Colors.WHITE);
        quickTextSize = (int) typedArray
                .getDimension(R.styleable.QuickIndexView_quickTextSize, DensityTool.dp2px(DEFAULT_QUICK_TEXT_SIZE));
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public QuickIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
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
            width = DensityTool.dp2px(context, 50);
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
        int x = getWidth() / 2;

        if(topBitmap != null)
        {
            bitmapRect.bottom = topBitmap.getHeight();
            bitmapRect.right = topBitmap.getWidth();
            outRect.left = x - letterLength / 2;
            outRect.right = x + letterLength / 2;
            outRect.top = y;
            outRect.bottom = y + letterLength;
            canvas.drawBitmap(topBitmap, bitmapRect, outRect, null);
        }

        y += letterLength;

        int fontY = (int) (letterLength / 2 - fontMetrics.top / 2 - fontMetrics.bottom / 2);
        for(int i = 0; i < letterList.size(); i++)
        {
            y += fontY;
            canvas.drawText(letterList.get(i), x, y, paint);
        }

        if(bottomBitmap != null)
        {
            bitmapRect.bottom = bottomBitmap.getHeight();
            bitmapRect.right = bottomBitmap.getWidth();
            outRect.left = x - letterLength / 2;
            outRect.right = x + letterLength / 2;
            outRect.top = y;
            outRect.bottom = y + letterLength;
            canvas.drawBitmap(bottomBitmap, bitmapRect, outRect, null);
        }
    }

    private int getLetterLength()
    {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int itemCount = letterList.size();
        if(topBitmap != null)
        {
            itemCount++;
        }
        if(bottomBitmap != null)
        {
            itemCount++;
        }

        return Math.min(width, (getHeight() - getPaddingTop() - getPaddingBottom()) / itemCount);
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
                if(showToast)
                {
                    toast.show();
                }
                if(onLetterChangeListener != null)
                {
                    onLetterChangeListener.onLetterChange(0, "A", this);
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

    public interface OnLetterChangeListener
    {
        void onLetterChange(int position, String text, QuickIndexView quickIndexView);
    }
}
