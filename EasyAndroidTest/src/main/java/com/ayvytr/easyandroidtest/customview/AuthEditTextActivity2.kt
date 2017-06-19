package com.ayvytr.easyandroidtest.customview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.ayvytr.easyandroid.tools.Colors
import com.ayvytr.easyandroid.view.custom.AuthEditText2
import com.ayvytr.easyandroidtest.R
import kotlinx.android.synthetic.main.activity_auth_edit_text2.*
import java.util.*

class AuthEditTextActivity2 : AppCompatActivity()
{
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_edit_text2);
        initView(savedInstanceState)
    }


    fun initView(savedInstanceState: Bundle?)
    {
        btnMaxLength?.setOnClickListener { authEditText?.setMaxLength(randomMaxLength) }
        btnSize?.setOnClickListener { authEditText?.layoutParams = randomLp }
        btnTextColor.setOnClickListener { authEditText?.setTextColor(randomColor) }
        btnTextSize.setOnClickListener { authEditText.setTextSize(random.nextInt(30)) }
        btnFrameColor.setOnClickListener { authEditText.setFrameColor(randomColor) }
    }

    private val randomMaxLength: Int
        get()
        {
            var i = random.nextInt(AuthEditText2.MAX_LENGTH)
            while (i < AuthEditText2.MIN_LENGTH)
            {
                i = random.nextInt(AuthEditText2.MAX_LENGTH)
            }

            return i
        }

    private val randomLp: LinearLayout.LayoutParams
        get()
        {
            return LinearLayout.LayoutParams(random.nextInt(400), random.nextInt(300))
        }

    private val randomColor: Int
        get()
        {
            return Colors.rgb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff))
        }
}
