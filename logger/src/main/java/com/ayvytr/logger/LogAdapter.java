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

interface LogAdapter
{
    void d(String tag, String message);

    void e(String tag, String message);

    void w(String tag, String message);

    void i(String tag, String message);

    void v(String tag, String message);

    void wtf(String tag, String message);
}