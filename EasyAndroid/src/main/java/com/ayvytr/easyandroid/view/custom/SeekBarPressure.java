package com.ayvytr.easyandroid.view.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ayvytr.easyandroid.R;


/**
 * 双向SeekBar, 直接继承View，有2个Thumb可供拖动, （有点拖动响应慢的问题，欢迎pull request）.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.6.0
 */

public class SeekBarPressure extends View
{
    private static final int CLICK_ON_LOW = 1;      //点击在前滑块上
    private static final int CLICK_ON_HIGH = 2;     //点击在后滑块上
    private static final int CLICK_IN_LOW_AREA = 3;
    private static final int CLICK_IN_HIGH_AREA = 4;
    private static final int CLICK_OUT_AREA = 5;
    private static final int CLICK_INVALID = 0;
    /*
     * private static final int[] PRESSED_STATE_SET = {
     * android.R.attr.state_focused, android.R.attr.state_pressed,
     * android.R.attr.state_selected, android.R.attr.state_window_focused, };
     */
    private static final int[] STATE_NORMAL = {};
    private static final int[] STATE_PRESSED =
            {
                    android.R.attr.state_pressed,
                    android.R.attr.state_window_focused
            };
    private Drawable hasScrollBarBg;        //滑动条滑动后背景图
    private Drawable notScrollBarBg;        //滑动条未滑动背景图
    private Drawable mThumbLow;         //前滑块
    private Drawable mThumbHigh;        //后滑块

    private int mScrollBarWidth;     //控件宽度=滑动条宽度+滑动块宽度
    private int mScrollBarHeight;    //滑动条高度

    private int mThumbWidth;        //滑动块宽度
    private int mThumbHeight;       //滑动块高度

    private int mOffsetLow = 0;     //前滑块中心坐标
    private int mOffsetHigh = 0;    //后滑块中心坐标
    private int mDistance = 0;      //总刻度是固定距离 两边各去掉半个滑块距离

    private int mThumbMarginTop = 30;   //滑动块顶部距离上边框距离，也就是距离字体顶部的距离

    private int mFlag = CLICK_INVALID;
    private OnSeekBarChangeListener mBarChangeListener;

    private int progressLow = 0;    //默认前滑块位置百分比
    private int progressHigh = 80;  //默认后滑块位置百分比

    private int minValue = 0;
    private int maxValue = 80;

    private boolean isNeedCallback = false;    //是不是用户输入
    private int mHalfThumbWidth;

    public SeekBarPressure(Context context)
    {
        this(context, null);
    }

    public SeekBarPressure(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SeekBarPressure(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        Resources resources = getResources();
        notScrollBarBg = resources.getDrawable(R.drawable.no_progress_bg);
        hasScrollBarBg = resources.getDrawable(R.drawable.has_progress_bg);
        mThumbLow = resources.getDrawable(R.drawable.progress_thumb);
        mThumbHigh = resources.getDrawable(R.drawable.progress_thumb);

        mThumbLow.setState(STATE_NORMAL);
        mThumbHigh.setState(STATE_NORMAL);

        mScrollBarWidth = notScrollBarBg.getIntrinsicWidth();
        mScrollBarHeight = notScrollBarBg.getIntrinsicHeight();

        mThumbWidth = mThumbLow.getIntrinsicWidth();
        mHalfThumbWidth = mThumbWidth / 2;
        mThumbHeight = mThumbLow.getIntrinsicHeight();
    }

    //默认执行，计算view的宽高,在onDraw()之前
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = measureWidth(widthMeasureSpec);
//        int height = measureHeight(heightMeasureSpec);
        mScrollBarWidth = width;
        mOffsetHigh = width - mHalfThumbWidth;
        mOffsetLow = mHalfThumbWidth;
        mDistance = width - mThumbWidth;

        mOffsetLow = (int) (((double) progressLow / maxValue * mDistance) + mHalfThumbWidth);
        mOffsetHigh = (int) (((double) progressHigh / maxValue * mDistance) + mHalfThumbWidth);
        setMeasuredDimension(width, mThumbHeight + mThumbMarginTop + 2);
    }

    private int measureWidth(int measureSpec)
    {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //wrap_content
        if(specMode == MeasureSpec.AT_MOST)
        {
        }
        //fill_parent或者精确值
        else if(specMode == MeasureSpec.EXACTLY)
        {
        }

        return specSize;
    }

    private int measureHeight(int measureSpec)
    {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int defaultHeight = 100;
        //wrap_content
        if(specMode == MeasureSpec.AT_MOST)
        {
        }
        //fill_parent或者精确值
        else if(specMode == MeasureSpec.EXACTLY)
        {
            defaultHeight = specSize;
        }

        return defaultHeight;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //没有用到
//        Paint text_Paint = new Paint();
//        text_Paint.setTextAlign(Paint.Align.CENTER);
//        text_Paint.setColor(Color.RED);
//        text_Paint.setTextSize(20);

        int top = mThumbMarginTop + mThumbHeight / 2 - mScrollBarHeight / 2;
        int bottom = top + mScrollBarHeight;

        //白色，不会动
        notScrollBarBg.setBounds(mHalfThumbWidth, top, mScrollBarWidth - mHalfThumbWidth, bottom);
        notScrollBarBg.draw(canvas);

        //蓝色，中间部分会动
        hasScrollBarBg.setBounds(mOffsetLow, top, mOffsetHigh, bottom);
        hasScrollBarBg.draw(canvas);

        //前滑块
        mThumbLow.setBounds(mOffsetLow - mHalfThumbWidth, mThumbMarginTop,
                mOffsetLow + mHalfThumbWidth, mThumbHeight + mThumbMarginTop);
        mThumbLow.draw(canvas);

        //后滑块
        mThumbHigh.setBounds(mOffsetHigh - mHalfThumbWidth, mThumbMarginTop,
                mOffsetHigh + mHalfThumbWidth, mThumbHeight + mThumbMarginTop);
        mThumbHigh.draw(canvas);

        progressLow = (mOffsetLow - mHalfThumbWidth) * maxValue / mDistance;
        progressHigh = (mOffsetHigh - mHalfThumbWidth) * maxValue / mDistance;
//        canvas.drawText(progressLow + "", mOffsetLow - 2 - 2, 15, text_Paint);
//        canvas.drawText(progressHigh + "", mOffsetHigh - 2, 15, text_Paint);

        if(mBarChangeListener != null)
        {
            if(isNeedCallback)
            {
                mBarChangeListener.onProgressChanged(this, progressLow, progressHigh);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        //按下
        if(e.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(mBarChangeListener != null)
            {
                mBarChangeListener.onProgressBefore();
                isNeedCallback = true;
            }
            mFlag = getAreaFlag(e);
//            Log.d(TAG, "e.getX: " + e.getX() + "mFlag: " + mFlag);
//            Log.d("ACTION_DOWN", "------------------");
            if(mFlag == CLICK_ON_LOW)
            {
                mThumbLow.setState(STATE_PRESSED);
            }
            else if(mFlag == CLICK_ON_HIGH)
            {
                mThumbHigh.setState(STATE_PRESSED);
            }
            else if(mFlag == CLICK_IN_LOW_AREA)
            {
                mThumbLow.setState(STATE_PRESSED);
                //如果点击0-mThumbWidth/2坐标
                if(e.getX() < 0 || e.getX() <= mHalfThumbWidth)
                {
                    mOffsetLow = mHalfThumbWidth;
                }
                else if(e.getX() > mScrollBarWidth - mHalfThumbWidth)
                {
                    mOffsetLow = mHalfThumbWidth + mDistance;
                }
                else
                {
                    mOffsetLow = (int) e.getX();
                }
            }
            else if(mFlag == CLICK_IN_HIGH_AREA)
            {
                mThumbHigh.setState(STATE_PRESSED);
                if(e.getX() >= mScrollBarWidth - mHalfThumbWidth)
                {
                    mOffsetHigh = mDistance + mHalfThumbWidth;
                }
                else
                {
                    mOffsetHigh = (int) e.getX();
                }
            }
            //设置进度条
            refresh();

        }
        else if(e.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(mFlag == CLICK_ON_LOW)
            {
                if(e.getX() < 0 || e.getX() <= mHalfThumbWidth)
                {
                    mOffsetLow = mHalfThumbWidth;
                }
                else if(e.getX() >= mScrollBarWidth - mHalfThumbWidth)
                {
                    mOffsetLow = mHalfThumbWidth + mDistance;
                    mOffsetHigh = mOffsetLow;
                }
                else
                {
                    mOffsetLow = (int) e.getX();
                    if(mOffsetHigh - mOffsetLow <= 0)
                    {
                        mOffsetHigh = (mOffsetLow <= mDistance + mHalfThumbWidth) ? (mOffsetLow) : (mDistance + mHalfThumbWidth);
                    }
                }
            }
            else if(mFlag == CLICK_ON_HIGH)
            {
                if(e.getX() < mHalfThumbWidth)
                {
                    mOffsetHigh = mHalfThumbWidth;
                    mOffsetLow = mHalfThumbWidth;
                }
                else if(e.getX() > mScrollBarWidth - mHalfThumbWidth)
                {
                    mOffsetHigh = mHalfThumbWidth + mDistance;
                }
                else
                {
                    mOffsetHigh = (int) e.getX();
                    if(mOffsetHigh - mOffsetLow <= 0)
                    {
                        mOffsetLow = (mOffsetHigh >= mHalfThumbWidth) ? (mOffsetHigh) : mHalfThumbWidth;
                    }
                }
            }
            //设置进度条
            refresh();
            //抬起
        }
        else if(e.getAction() == MotionEvent.ACTION_UP)
        {
            mThumbLow.setState(STATE_NORMAL);
            mThumbHigh.setState(STATE_NORMAL);

            if(mBarChangeListener != null)
            {
                mBarChangeListener.onProgressAfter();
            }
            //这两个for循环 是用来自动对齐刻度的，注释后，就可以自由滑动到任意位置
//            for (int i = 0; i < money.length; i++) {
//                 if(Math.abs(mOffsetLow-i* ((mScrollBarWidth-mThumbWidth)/ (money.length-1)))<=(mScrollBarWidth-mThumbWidth)/(money.length-1)/2){
//                     mprogressLow=i;
//                     mOffsetLow =i* ((mScrollBarWidth-mThumbWidth)/(money.length-1));
//                     invalidate();
//                     break;
//                }
//            }
//
//            for (int i = 0; i < money.length; i++) {
//                  if(Math.abs(mOffsetHigh-i* ((mScrollBarWidth-mThumbWidth)/(money.length-1) ))<(mScrollBarWidth-mThumbWidth)/(money.length-1)/2){
//                      mprogressHigh=i;
//                       mOffsetHigh =i* ((mScrollBarWidth-mThumbWidth)/(money.length-1));
//                       invalidate();
//                       break;
//                }
//            }
        }
        return true;
    }

    public int getAreaFlag(MotionEvent e)
    {

        int top = mThumbMarginTop;
        int bottom = mThumbHeight + mThumbMarginTop;
        if(e.getY() >= top && e.getY() <= bottom && e.getX() >= (mOffsetLow - mHalfThumbWidth) && e
                .getX() <= mOffsetLow + mHalfThumbWidth)
        {
            return CLICK_ON_LOW;
        }
        else if(e.getY() >= top && e.getY() <= bottom && e
                .getX() >= (mOffsetHigh - mHalfThumbWidth) && e
                .getX() <= (mOffsetHigh + mHalfThumbWidth))
        {
            return CLICK_ON_HIGH;
        }
        else if(e.getY() >= top
                && e.getY() <= bottom
                && ((e.getX() >= 0 && e.getX() < (mOffsetLow - mHalfThumbWidth)) || ((e
                .getX() > (mOffsetLow + mHalfThumbWidth))
                && e.getX() <= ((double) mOffsetHigh + mOffsetLow) / 2)))
        {
            return CLICK_IN_LOW_AREA;
        }
        else if(e.getY() >= top
                && e.getY() <= bottom
                && (((e.getX() > ((double) mOffsetHigh + mOffsetLow) / 2) && e
                .getX() < (mOffsetHigh - mHalfThumbWidth)) || (e
                .getX() > (mOffsetHigh + mHalfThumbWidth) && e.getX() <= mScrollBarWidth)))
        {
            return CLICK_IN_HIGH_AREA;
        }
        else if(!(e.getX() >= 0 && e.getX() <= mScrollBarWidth && e.getY() >= top && e
                .getY() <= bottom))
        {
            return CLICK_OUT_AREA;
        }
        else
        {
            return CLICK_INVALID;
        }
    }

    //更新滑块
    private void refresh()
    {
        invalidate();
    }

    //设置前滑块的值
    public void setProgressLow(int progressLow)
    {
        this.progressLow = progressLow;
        isNeedCallback = false;
        refresh();
    }

    //设置后滑块的值
    public void setProgressHigh(int progressHigh)
    {
        this.progressHigh = progressHigh;
        isNeedCallback = false;
        refresh();
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener mListener)
    {
        this.mBarChangeListener = mListener;
    }

    public int getProgressLow()
    {
        return progressLow;
    }

    public int getProgressHigh()
    {
        return progressHigh;
    }

    //回调函数，在滑动时实时调用，改变输入框的值
    public interface OnSeekBarChangeListener
    {
        //滑动前
        void onProgressBefore();

        //滑动时
        void onProgressChanged(SeekBarPressure seekBar, int progressLow,
                               int progressHigh);

        //滑动后
        void onProgressAfter();
    }

}
