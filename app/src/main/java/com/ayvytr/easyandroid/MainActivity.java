package com.ayvytr.easyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ayvytr.easyandroidlibrary.Easy;
import com.ayvytr.easyandroidlibrary.tools.withcontext.ClipboardTool;
import com.ayvytr.easyandroidlibrary.tools.withcontext.Managers;
import com.ayvytr.easyandroidlibrary.tools.withcontext.ResTool;
import com.ayvytr.easyandroidlibrary.tools.withcontext.ToastTool;
import com.ayvytr.logger.L;
import com.ayvytr.logger.LogLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Easy.getDefault().init(getApplicationContext());
        init();
//        log();
//        log1();
//        log2();
//        log3();
//        log4();
//        log5();
//        log6();
//        log7();
        log8();
    }

    private void log8()
    {
        L.getSettings().logLevel(LogLevel.NONE);
        L.e();
        L.e("ia");
        L.e(1);
        L.e("i1", 2);
    }

    private void log7()
    {
        L.getSettings().justShowMessage(true);
        L.i();
        L.w();
        L.wtf();
        L.v();
    }

    private void log6()
    {
        L.getSettings().justShowMessage(true);
        L.w("aa");
        L.w("aa", 1);
    }

    private void log5()
    {
        L.getSettings().showBottomBorder(true);
        L.e("Msg");
        L.w("Msg");
    }

    private void log4()
    {
        L.getSettings().methodCount(0).showBottomBorder(true);
        L.w(1, "aA");
    }

    private void log3()
    {
        L.getSettings().methodCount(10).showBottomBorder(true).methodOffset(5);
        L.e();
    }

    private void log2()
    {
//        L.t("MyTag").e(1);
//        L.getSettings().tag("MyTag");
        L.getSettings().hideThreadInfo();
        L.e(1, 2);
    }

    private void log()
    {
        //        L.getSettings().showBottomBorder(true);
//        L.getSettings().tag("mytag");
//        L.getSettings().tag("mytag").justShowMessage(true);
//        L.getSettings().tag("mytag").methodCount(55).showBottomBorder(true);
//        L.e("MyLog");
//        L.w("Message");
//
////        L.t("taggg").e("aaa");
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                SystemClock.sleep(2000);
//                L.w("Thread");
//
//                L.i();
//                L.w();
//                L.wtf();
//                L.v();
//            }
//        }).start();

        L.e("message", "arg1", 2, "arg3");
        L.w("message", "arg1", 2, "arg3");
        L.i("message", "arg1", 2, "arg3");
    }

    private void log1()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");

        HashMap<String, Integer> map = new HashMap<>();
        map.put("KA", 1);
        map.put("KB", 2);
        map.put("KC", 3);

        TreeSet<String> set = new TreeSet<>();
        set.add("AAA");
        set.add("BBB");
        set.add("CCC");

//        L.i("Array", 1, 2, "aa", "bb");
//        L.i("List", list);
//        L.v("Map", map);
//        L.e("Set", set);
//        L.w("Collection", list, map, set);
        L.i(1, 2, "aa", "bb");
        L.i(list);
        L.v(map);
        L.e(set);
        L.w(list, map, set);
    }

    private void init()
    {
        Managers.getVibrator().vibrate(1000);
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
}
