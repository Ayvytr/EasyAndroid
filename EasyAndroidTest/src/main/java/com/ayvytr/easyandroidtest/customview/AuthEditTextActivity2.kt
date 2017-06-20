package com.ayvytr.easyandroidtest.customview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.ayvytr.easyandroid.tools.Colors
import com.ayvytr.easyandroid.view.custom.NewAuthEditText
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
        btnFrameWidth.setOnClickListener { authEditText.setFrameWidth(random.nextInt(20)) }
        btnInputType.setOnClickListener { authEditText.setInputType(randomInputType) }
        btnPasswordString.setOnClickListener { authEditText.setPasswordString(randomPasswordString) }
    }

    private val randomMaxLength: Int
        get()
        {
            var i = random.nextInt(NewAuthEditText.MAX_LENGTH)
            while (i < NewAuthEditText.MIN_LENGTH)
            {
                i = random.nextInt(NewAuthEditText.MAX_LENGTH)
            }

            return i
        }

    private val randomLp: LinearLayout.LayoutParams
        get()
        {
            return LinearLayout.LayoutParams(random.nextInt(500), random.nextInt(400))
        }

    private val randomColor: Int
        get()
        {
            return Colors.rgb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff))
        }

    private val randomInputType: NewAuthEditText.InputType
        get() = NewAuthEditText.InputType.valueOf(random.nextInt(5))

    private val randomPasswordString: String
        get()
        {
            val i = random.nextInt(6)
            when (i)
            {
                0    -> return "·"
                1    -> return "*"
                2    -> return "CCcccccccc"
                4    -> return ""
                else -> return "aaa"
            }
        }
}
