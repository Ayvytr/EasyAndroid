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


/**
 * L is a wrapper of {@link android.util.Log}
 * But more pretty, simple and powerful.
 */
public final class L
{
    static final String DEFAULT_TAG = "PRETTYLOGGER";

    private static Settings settings = new Settings();
    private static Printer printer = new Printer(settings);

    private L()
    {
        throw new UnsupportedInitializationException();
    }

    public static IPrinter t(String tag)
    {
        return printer.t(tag);
    }
    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static Settings getSettings()
    {
        return settings;
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


    public static void v(Object... objects)
    {
        printer.v(objects);
    }

    public static void d(Object... objects)
    {
        printer.d(objects);
    }

    public static void i(Object... objects)
    {
        printer.i(objects);
    }

    public static void w(Object... objects)
    {
        printer.w(objects);
    }

    public static void e(Object... objects)
    {
        printer.e(objects);
    }

    public static void wtf(Object... objects)
    {
        printer.wtf(objects);
    }

    public static void v()
    {
        printer.v();
    }

    public static void d()
    {
        printer.d();
    }

    public static void i()
    {
        printer.i();
    }

    public static void w()
    {
        printer.w();
    }

    public static void e()
    {
        printer.e();
    }

    public static void wtf()
    {
        printer.wtf();
    }
}
