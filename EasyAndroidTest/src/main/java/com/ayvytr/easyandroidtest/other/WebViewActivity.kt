package com.ayvytr.easyandroidtest.other

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ayvytr.easyandroidtest.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        progressWebView.loadUrl("http://www.baidu.com")
        progressWebView.setShowProgressBar(false)
    }

    override fun onDestroy()
    {
        progressWebView.destroy()
        super.onDestroy()
    }
}
