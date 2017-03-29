/*
 * Copyright 2016 Ayvytr
 * From: github:orhanobut/logger
 * ________________________________________________________________________
 *
 * Copyright 2015 Orhan Obut
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This software contains code derived from the following Android classes:
 * android.util.Log, android.text.TextUtils.
 */
package com.ayvytr.logger;

import android.support.annotation.NonNull;

public final class Settings
{
    private int methodCount = 2;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;
    //是否显示底部Border，默认不显示
    private boolean showBottomLogBorder;
    private LogAdapter logAdapter;

    /**
     * Determines to how logs will be printed
     */
    private LogLevel logLevel = LogLevel.FULL;
    private String tag = L.DEFAULT_TAG;
    private boolean justShowMessage;

    public Settings hideThreadInfo()
    {
        showThreadInfo = false;
        return this;
    }

    public Settings methodCount(int methodCount)
    {
        if(methodCount < 0)
        {
            methodCount = 0;
        }
        this.methodCount = methodCount;
        return this;
    }

    public Settings logLevel(LogLevel logLevel)
    {
        this.logLevel = logLevel;
        return this;
    }

    public Settings methodOffset(int offset)
    {
        this.methodOffset = offset;
        return this;
    }

    public int getMethodCount()
    {
        return methodCount;
    }

    public boolean isShowThreadInfo()
    {
        return showThreadInfo;
    }

    public LogLevel getLogLevel()
    {
        return logLevel;
    }

    public int getMethodOffset()
    {
        return methodOffset;
    }

    public Settings logAdapter(LogAdapter logAdapter)
    {
        this.logAdapter = logAdapter;
        return this;
    }

    public LogAdapter getLogAdapter()
    {
        if(logAdapter == null)
        {
            logAdapter = new AndroidLogAdapter();
        }
        return logAdapter;
    }

    public void reset()
    {
        methodCount = 2;
        methodOffset = 0;
        showThreadInfo = true;
        logLevel = LogLevel.FULL;
    }

    public boolean isShowBottomLogBorder()
    {
        return showBottomLogBorder;
    }

    public Settings showBottomBorder(boolean showBottomBorder)
    {
        this.showBottomLogBorder = showBottomBorder;
        return this;
    }

    public Settings tag(@NonNull String tag)
    {
        if(tag == null)
        {
            throw new NullPointerException();
        }

        this.tag = tag;
        return this;
    }

    public String getTag()
    {
        return tag;
    }

    public Settings justShowMessage(boolean justShowMessage)
    {
        this.justShowMessage = justShowMessage;
        return this;
    }

    public boolean isJustShowMessage()
    {
        return justShowMessage;
    }
}
