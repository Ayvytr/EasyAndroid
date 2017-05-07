package com.ayvytr.easyandroidtest.other;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ayvytr.easyandroid.tools.BitmapTool;
import com.ayvytr.easyandroid.tools.withcontext.ResTool;
import com.ayvytr.easyandroidtest.R;

/**
 * 图片测试Activity，测试图片缩放，旋转，以及转换
 */
public class BitmapActivity extends AppCompatActivity
{

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    private ImageView iv9;
    private ImageView iv7;
    private ImageView iv8;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        initView();
    }

    private void initView()
    {
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv6 = (ImageView) findViewById(R.id.iv6);
        iv7 = (ImageView) findViewById(R.id.iv7);
        iv8 = (ImageView) findViewById(R.id.iv8);
        iv9 = (ImageView) findViewById(R.id.iv9);

        Drawable drawable = ResTool.getDrawable(R.mipmap.ic_launcher);
        iv1.setImageDrawable(drawable);
        iv2.setImageBitmap(BitmapTool.toBitmap(R.mipmap.ic_launcher));
        iv3.setImageBitmap(BitmapTool.toBitmap(drawable));

        iv4.setImageBitmap(BitmapTool.zoom(drawable, 20, 30));
        iv5.setImageBitmap(BitmapTool.zoom(BitmapTool.toBitmap(drawable), 50, 30));
        iv6.setImageBitmap(BitmapTool.zoom(drawable, -1));

        iv7.setImageDrawable(BitmapTool.rotate2Drawable(drawable, 90));
        iv8.setImageBitmap(BitmapTool.rotate(drawable, 180));
        iv9.setImageBitmap(BitmapTool.rotate(BitmapTool.toBitmap(drawable), -90));
    }
}
