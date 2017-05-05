package com.ayvytr.easyandroidtest;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ayvytr.easyandroid.Easy;
import com.ayvytr.easyandroid.tools.Colors;
import com.ayvytr.easyandroid.tools.Convert;
import com.ayvytr.easyandroid.tools.withcontext.ClipboardTool;
import com.ayvytr.easyandroid.tools.withcontext.Managers;
import com.ayvytr.easyandroid.tools.withcontext.Res;
import com.ayvytr.easyandroid.tools.withcontext.ResTool;
import com.ayvytr.easyandroid.tools.withcontext.ScreenTool;
import com.ayvytr.easyandroid.tools.withcontext.ToastTool;
import com.ayvytr.easyandroid.view.activity.BaseActivity;
import com.ayvytr.easyandroid.view.custom.LeftCenterGravityTextView;
import com.ayvytr.easyandroid.view.custom.RightCenterGravityTextView;
import com.ayvytr.easyandroidtest.stickyheader.StickyHeaderActivity;
import com.ayvytr.logger.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
{

    @BindView(R.id.tv2)
    LeftCenterGravityTextView tv2;
    @BindView(R.id.tv3)
    RightCenterGravityTextView tv3;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv2)
    ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Easy.getDefault().init(getContext());
        L.getSettings().justShowMessage(true);
        init();
    }

    private void init()
    {
        findViewById(R.id.tv).setBackgroundColor(Colors.PINK);

        tv2.append(":");
        Point point = ScreenTool.getPoint();
        tv2.append(Convert.toString(point.x));
        tv2.append(",");
        tv2.append(Convert.toString(point.y));

        tv3.append(":(");
        tv3.append(Convert.toString(ScreenTool.getScreenWidth()));
        tv3.append(",");
        tv3.append(Convert.toString(ScreenTool.getScreenHeight()));
        tv3.append(")");

        Drawable[] drawableArray = Res.getDrawableArray(R.array.testArray);
        iv.setImageDrawable(drawableArray[0]);
        int[] drawableIdArray = Res.getDrawableIdArray(R.array.testArray);
        iv2.setImageResource(drawableIdArray[0]);
    }

    public void onGetClipboard(View view)
    {
        String text = ClipboardTool.getText("没获得");
        ToastTool.show(text);
    }

    public void onSetClipboard(View view)
    {
        ClipboardTool.setText("hello");
        ToastTool.show("已设置Hello到剪贴板");
    }

    public void onGetAppName(View view)
    {
        Managers.getVibrator().vibrate(1000);
        String string = ResTool.getString(R.string.app_name);
        ToastTool.show("获取到Res中APP名称:" + string);
        findViewById(R.id.btnPerform).performClick();
    }

    public void onSeePopupWindow(View view)
    {
        startActivity(new Intent(this, PopupWindowActivity.class));
    }

    public void onPerformClick(View view)
    {
        ToastTool.show("performClick");
    }

    public void onSeePackages(View view)
    {
        startActivity(new Intent(this, PackagesActivity.class));
    }

    public void onSystemInfo(View view)
    {
        startActivity(new Intent(this, SystemInfoActivity.class));
    }

    public void onBitmap(View view)
    {
        startActivity(new Intent(this, BitmapActivity.class));
    }

    public void onTestFullScreen(View view)
    {
        startActivity(new Intent(this, TestFullScreenActivity.class));
    }

    public void onStickyHeader(View view)
    {
        startActivity(new Intent(this, StickyHeaderActivity.class));
    }

    public void onCustomView(View view)
    {
        startActivity(new Intent(getContext(), CustomViewActivity.class));
    }
}
