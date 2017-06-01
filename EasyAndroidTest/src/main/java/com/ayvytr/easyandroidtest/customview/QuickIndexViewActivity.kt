package com.ayvytr.easyandroidtest.customview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ayvytr.easyandroid.tools.Colors
import com.ayvytr.easyandroid.tools.withcontext.Res
import com.ayvytr.easyandroidtest.R
import kotlinx.android.synthetic.main.activity_quick_index_view.*
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
    }

    private val randomColor: Int
        get() = Colors.rgb(random.nextInt(0xff), random.nextInt(0xff), random.nextInt(0xff))

}
