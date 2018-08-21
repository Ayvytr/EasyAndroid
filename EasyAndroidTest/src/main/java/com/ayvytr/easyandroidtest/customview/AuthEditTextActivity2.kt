package com.ayvytr.easyandroidtest.customview

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.ayvytr.easyandroid.tools.withcontext.ToastTool
import com.ayvytr.easyandroid.view.custom.AuthEditText
import com.ayvytr.easyandroidtest.R
import kotlinx.android.synthetic.main.activity_auth_edit_text2.*
import java.util.*

class AuthEditTextActivity2 : AppCompatActivity()
{
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_edit_text2)
        initView(savedInstanceState)
    }


    fun initView(savedInstanceState: Bundle?)
    {
        btnMaxLength?.setOnClickListener { authEditText?.maxLength = randomMaxLength }
        btnSize?.setOnClickListener { authEditText?.layoutParams = randomLp }
        btnTextColor.setOnClickListener { authEditText?.textColor = randomColor }
        btnTextSize.setOnClickListener { authEditText.textSize = random.nextInt(30) }
        btnFrameColor.setOnClickListener { authEditText.frameColor = randomColor }
        btnFrameWidth.setOnClickListener { authEditText.frameWidth = random.nextInt(20) }
        btnInputType.setOnClickListener { authEditText.inputType = randomInputType }
        btnPasswordString.setOnClickListener { authEditText.passwordString = randomPasswordString }
        authEditText.setOnInputChangeListener(object : AuthEditText.OnInputChangeListener
                                              {
                                                  override fun onFinished(authEditText: AuthEditText, s: String)
                                                  {
                                                  }

                                                  override fun onTextChanged(isFinished: Boolean, s: String)
                                                  {
                                                      ToastTool.show(s)
                                                  }
                                              })

    }

    private val randomMaxLength: Int
        get()
        {
            var i = random.nextInt(AuthEditText.MAX_LENGTH)
            while (i < AuthEditText.MIN_LENGTH)
            {
                i = random.nextInt(AuthEditText.MAX_LENGTH)
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
            return Color.rgb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff))
        }

    private val randomInputType: AuthEditText.InputType
        get() = AuthEditText.InputType.valueOf(random.nextInt(5))

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
