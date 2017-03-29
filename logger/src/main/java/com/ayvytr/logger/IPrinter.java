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

public interface IPrinter
{
    IPrinter t(String tag);

    void d(String message, Object... args);

    void e(String message, Object... args);

    void e(Throwable throwable, String message, Object... args);

    void w(String message, Object... args);

    void i(String message, Object... args);

    void v(String message, Object... args);

    void wtf(String message, Object... args);

    void json(String json);

    void xml(String xml);

    void log(int priority, String tag, String message, Throwable throwable);

    //最简Log, 以下几个无参Log使用
    void prettyLog(int priority, String tag, String message);

    void v();
    void d();
    void i();
    void w();
    void e();
    void wtf();

    void v(Object... objects);
    void d(Object... objects);
    void i(Object... objects);
    void w(Object... objects);
    void e(Object... objects);
    void wtf(Object... objects);
}
