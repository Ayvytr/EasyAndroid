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

package com.ayvytr.easyandroidlibrary.logger;

/**
 * L is a wrapper of {@link android.util.Log}
 * But more pretty, simple and powerful.
 */
public final class L
{
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    private static final String DEFAULT_TAG = "PRETTYLOGGER";

    private static LPrinter printer = new LPrinter();

    //no instance
    private L()
    {
    }

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static LSettings init()
    {
        return init(DEFAULT_TAG);
    }

    /**
     * It is used to change the tag
     *
     * @param tag is the given string which will be used in L as TAG
     * @return LSettings
     */
    public static LSettings init(String tag)
    {
        printer = new LPrinter();
        return printer.init(tag);
    }

    public static void resetSettings()
    {
        printer.resetSettings();
    }

    public static Printer t(String tag)
    {
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount)
    {
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount)
    {
        return printer.t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable)
    {
        printer.log(priority, tag, message, throwable);
    }

    public static void d(String message, Object... args)
    {
        printer.d(message, args);
    }

    public static void d(Object object)
    {
        printer.d(object);
    }

    public static void e(String message, Object... args)
    {
        printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args)
    {
        printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args)
    {
        printer.i(message, args);
    }

    public static void v(String message, Object... args)
    {
        printer.v(message, args);
    }

    public static void w(String message, Object... args)
    {
        printer.w(message, args);
    }

    public static void wtf(String message, Object... args)
    {
        printer.wtf(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json)
    {
        printer.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml)
    {
        printer.xml(xml);
    }

}
