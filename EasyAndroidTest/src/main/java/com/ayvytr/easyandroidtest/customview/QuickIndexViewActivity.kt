package com.ayvytr.easyandroidtest.customview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ayvytr.easyandroid.tools.withcontext.DensityTool
import com.ayvytr.easyandroid.tools.withcontext.Res
import com.ayvytr.easyandroidtest.R
import kotlinx.android.synthetic.main.activity_quick_index_view.*
import org.jetbrains.anko.toast
import java.util.*

class QuickIndexViewActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_index_view)
        initView()
    }

    private val random = Random()

    private val drawables = intArrayOf(0, android.R.drawable.ic_menu_search, android.R.drawable.ic_menu_add, android.R.drawable.ic_menu_call, android.R.drawable.ic_menu_camera, android.R.drawable.ic_menu_day)

    val randomDrawable: Drawable?
        get()
        {
            val i = random.nextInt(drawables.size)
            if (i == 0)
            {
                return null
            }

            return Res.getDrawable(drawables[i])
        }


    fun initView()
    {
        quickIndexView?.setOnLetterChangeListener { position, text, quickIndexView -> }
        btnTextColor?.setOnClickListener {
            val rgb = randomColor
            quickIndexView?.textColor = rgb
        }
        btnTopDrawable.setOnClickListener {
            quickIndexView?.topDrawable = randomDrawable
        }
        btnBottomDrawable.setOnClickListener {
            quickIndexView?.bottomDrawable = randomDrawable
        }
        btnBackground.setOnClickListener {
            quickIndexView.setBackgroundColor(randomColor)
        }
        btnShowToast.setOnClickListener {
            quickIndexView.isShowToast = !quickIndexView.isShowToast
            //这里有问题，不能在屏幕上同时显示多个Toast
            toast("已经更改了，触摸字母索引试试吧!")
        }
        btnToastTextColor.setOnClickListener {
            quickIndexView.quickTextColor = randomColor
        }
        btnToastBackground.setOnClickListener {
            quickIndexView.quickBackground = ColorDrawable(randomColor)
        }
        btnToastWidth.setOnClickListener {
            quickIndexView.quickWidth = randomToastPx
        }
        btnToastHeight.setOnClickListener {
            quickIndexView.quickHeight = randomToastPx
        }
        btnNullData.setOnClickListener {
            quickIndexView.bottomDrawable = null
            quickIndexView.topDrawable = null
            quickIndexView.letterList = null
        }
        btnRestoreData.setOnClickListener {
            quickIndexView.setTopDrawable(android.R.drawable.ic_menu_search)
            quickIndexView.setBottomDrawable(android.R.drawable.dark_header)
            quickIndexView.letterList = arrayListOf("a", "B", "c")
        }
    }

    private val randomColor: Int
        get() = Color.rgb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff))

    private val randomToastPx: Int
        get()
        {
            var i = random.nextInt(1000)
            while (i < DensityTool.dp2px(50))
            {
                i = random.nextInt(1000)
            }

            return i
        }
}
