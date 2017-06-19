package com.ayvytr.easyandroidtest

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by Do on 2017/6/19.
 */

fun AppCompatActivity.getContext(): Context
{
    return this
}

fun AppCompatActivity.getActivity(): Activity
{
    return this
}
