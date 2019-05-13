package com.ayvytr.ktx.ui

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

/**
 * Activity 相关的Kotlin扩展方法
 * <p>
 *
 * @author ['s GitHub](https://github.com/Ayvytr)
 * @since 0.1.0
 */

/**
 * 获取 AppCompatActivity 的 Context（实际获取的是Activity自身）
 */
fun AppCompatActivity.getContext(): Context
{
    return this
}

/**
 * 获取 AppCompatActivity 自身
 */
fun AppCompatActivity.getActivity(): AppCompatActivity
{
    return this
}

/**
 * 获取 Activity 的 Context（实际获取的是Activity自身）
 */
fun Activity.getContext(): Context
{
    return this
}

/**
 * 获取 Activity 自身
 */
fun Activity.getActivity(): Activity
{
    return this
}

fun Activity.showActionBar()
{
    if (this is AppCompatActivity)
    {
        supportActionBar?.show()
    }
    else
    {
        actionBar?.show()
    }
}

fun Activity.hideActionBar()
{
    if (this is AppCompatActivity) supportActionBar?.hide() else actionBar?.hide()
}

/**
 * Activity切换为全屏/非全屏
 *
 * 2018-1-5 09:30 修改为2个参数，因为一般情况下，全屏和非全屏，ActionBar同时也有必要进行相应的隐藏/显示操作，所以进行了专门的修改
 * @param isFullScreen true 是全屏; false 不是全屏
 * @param withActionBar true 伴随全屏和非全屏，ActionBar分别进行隐藏和显示; false ActionBar不进行任何操作
 */
fun Activity.fullscreen(isFullScreen: Boolean, withActionBar: Boolean = true)
{
    val attrs = window.attributes
    if (isFullScreen)
    {
        attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
        if (withActionBar) hideActionBar()
    }
    else
    {
        attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
        if (withActionBar) showActionBar()
    }
    window.attributes = attrs
}

fun Activity.isFullscreen(): Boolean
{
    return window.attributes.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == WindowManager.LayoutParams.FLAG_FULLSCREEN
}

/**
 * 切换fragment
 *
 * @param targetFragmentClass 目标Fragment
 * @param id 容纳Fragment的ViewGroup
 * @param currentFragment 当前Fragment
 * @since 2.3.1
 */
fun FragmentActivity.switchFragment(targetFragmentClass: Class<out Fragment>,
                                    @IdRes id: Int, currentFragment: Fragment? = null): Fragment {
    val targetFragment = supportFragmentManager.findFragmentByTag(targetFragmentClass.name)
            ?: targetFragmentClass.newInstance()

    val transaction = supportFragmentManager.beginTransaction()
    if (!targetFragment.isAdded) {
        transaction.add(id, targetFragment, targetFragment::class.java.name)
    }

    transaction.show(targetFragment)
    //如果有多个Fragment切换时，findFragmentById得到的就不是你想要隐藏的Fragment了，猜想是之前切换隐藏的Fragment没有销毁，得到的
    //不是当前要影藏的Fragment
    //        supportFragmentManager.findFragmentById(id)?.let {
    //            transaction.hide(it)
    //            L.e("find ", it::class.java.name)
    //        }

    //当targetFragment=currentFragment时，不进行hide（场景是切换时多次按了同一个menu item，界面上fragment先显示而后隐藏了）
    if (currentFragment != targetFragment) {
        currentFragment?.let { transaction.hide(it) }
    }

    transaction.commit()

    return targetFragment
}

