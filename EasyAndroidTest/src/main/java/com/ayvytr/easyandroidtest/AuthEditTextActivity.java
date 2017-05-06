package com.ayvytr.easyandroidtest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ayvytr.easyandroid.tools.Convert;
import com.ayvytr.easyandroid.tools.withcontext.ToastTool;
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
        authEditText.setOnInputFinishedListener(new AuthEditText.OnInputFinishedListener() {
            @Override
            public void onFinish(AuthEditText authEditText, String s)
            {
                ToastTool.show(s);
            }
        });
        authEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                authEditText.requestEditTextFocus();
            }
        });
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

    public void onChangeTextLength(View view)
    {
        new MaterialDialog.Builder(getContext())
                .items(R.array.auth_edit_text_length)
                .alwaysCallInputCallback()
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                                               CharSequence text)
                    {
                        authEditText.setTextLength(Convert.toInt(text.toString()));
                        return true;
                    }
                }).show();
    }

    public void onAuthType(View view)
    {
        new MaterialDialog.Builder(getContext())
                .items(R.array.auth_edit_text_auth_type)
                .alwaysCallInputCallback()
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                                               CharSequence text)
                    {
                        AuthEditText.AuthType type = AuthEditText.AuthType.NUMBER;
                        switch(which)
                        {
                            case 0:
                                type = AuthEditText.AuthType.NUMBER;
                                break;
                            case 1:
                                type = AuthEditText.AuthType.PASSWORD;
                                break;
                            case 2:
                                type = AuthEditText.AuthType.VISIBLE_PASSWORD;
                                break;
                            case 3:
                                type = AuthEditText.AuthType.NUMBER_PASSWORD;
                                break;
                            case 4:
                                type = AuthEditText.AuthType.DEFAULT;
                                break;
                        }
                        authEditText.setAuthType(type);
                        return true;
                    }
                }).show();
    }

    public void onChangeBackground(View view)
    {
        authEditText.setTextViewDrawableRes(R.mipmap.ic_launcher);
    }

    public void onPasswordStr(View view)
    {
        authEditText.setPasswordStr("*");
    }
}
