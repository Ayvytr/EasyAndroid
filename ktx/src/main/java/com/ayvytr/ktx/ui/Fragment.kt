package com.ayvytr.ktx.ui

import android.support.annotation.IdRes
import android.support.v4.app.Fragment

/**
 * @author ayvytr
 * @since 2.3.1
 */

/**
 * 切换fragment
 *
 * @param targetFragmentClass 目标Fragment
 * @param id 容纳Fragment的ViewGroup
 * @param currentFragment 当前Fragment
 */
fun Fragment.switchFragment(targetFragmentClass: Class<out Fragment>,
                            @IdRes id: Int, currentFragment: Fragment? = null): Fragment {
    val fragmentManager = fragmentManager!!
    val targetFragment = fragmentManager.findFragmentByTag(targetFragmentClass.name)
            ?: targetFragmentClass.newInstance()

    val transaction = fragmentManager.beginTransaction()
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