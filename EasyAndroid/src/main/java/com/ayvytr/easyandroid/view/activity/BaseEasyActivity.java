package com.ayvytr.easyandroid.view.activity;

import android.os.Bundle;

/**
 * 继承 {@link BaseActivity} 的抽象类，onCreate中添加了 {@link #init(Bundle)} 方法，其中包含
 * {@link #initExtra()}, {@link #initView(Bundle)}, {@link #initData()}, 分别获取Bundle中的数据，
 * 初始化View(如果要使用BufferKnife，可以在这个方法绑定)，初始化数据，Activity的content view通过方法
 * {@link #getContentViewRes()} 获取.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.7.8
 */

public abstract class BaseEasyActivity extends BaseActivity
{

}
