package com.ayvytr.easyandroidtest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ayvytr.easyandroid.view.activity.BaseEasyActivity;
import com.ayvytr.easyandroid.view.custom.AuthEditText;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthEditTextActivity extends BaseEasyActivity
{
    @BindView(R.id.authEditText)
    AuthEditText authEditText;
    private Random random;

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_auth_edit_text;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
        random = new Random();
    }

    public void onTextColor(View view)
    {
        authEditText.setTextColor(getRandomColor());
    }

    public int getRandomColor()
    {
        return Color.argb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff),
                random.nextInt(0xff));
    }

    public void onFrameColor(View view)
    {
        authEditText.setFrameColor(getRandomColor());
    }

    public void onTextSize(View view)
    {
        authEditText.setTextSize(random.nextInt(60));
    }

    public void onChangeSize(View view)
    {
        ViewGroup.LayoutParams lp = authEditText.getLayoutParams();
        lp.width = 800;
        lp.height = 500;
        authEditText.setLayoutParams(lp);
        authEditText.invalidate();
    }

    public void onClearText(View view)
    {
        authEditText.clearText();
    }
}
