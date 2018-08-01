package com.ayvytr.easyandroidtest.network;

import android.os.Bundle;
import android.widget.TextView;

import com.ayvytr.easyandroid.tools.withcontext.ToastTool;
import com.ayvytr.easyandroid.view.activity.BaseActivity;
import com.ayvytr.easyandroidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class NetworkActivity extends BaseActivity
{
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tvContent)
    TextView tvContent;

    @Override
    protected int getContentLayoutRes()
    {
        return R.layout.activity_network;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        ButterKnife.bind(this);
        Network.getWeather("深圳", new Consumer<Weather>()
        {
            @Override
            public void accept(Weather weather) throws Exception
            {
                Weather.DataBean data = weather.getData();
                ToastTool.showLong(String.format("温度%s℃ %s", data.getWendu(), data.getGanmao()));
                tv.setText(data.getWendu());
                tvContent.setText(data.getGanmao());
            }
        });
    }
}
