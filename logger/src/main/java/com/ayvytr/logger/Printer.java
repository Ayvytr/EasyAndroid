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

import android.support.annotation.IntDef;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

final class Printer implements IPrinter
{
    private static final int VERBOSE = 2;
    private static final int DEBUG = 3;
    private static final int INFO = 4;
    private static final int WARN = 5;
    private static final int ERROR = 6;
    private static final int ASSERT = 7;

    @IntDef({DEBUG, ERROR, ASSERT, INFO, VERBOSE, WARN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {}

    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    private static final int CHUNK_SIZE = 4000;

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;

    /**
     * The minimum stack trace index, starts at this class after two native calls.
     */
    private static final int MIN_STACK_OFFSET = 3;

    /**
     * Drawing toolbox
     */
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char TOP_LEFT_CONNECT_CORNER = '╠';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String TOP_CONNECT_BORDER = TOP_LEFT_CONNECT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    private static final int CONNECT_BORDER_INTERVAL = 1000;

    private long lastTimeMillis;

    /**
     * Localize single tag and method count for each thread
     */
    private final ThreadLocal<String> localTag = new ThreadLocal<String>();
    private final ThreadLocal<Integer> localMethodCount = new ThreadLocal<Integer>();

    /**
     * It is used to determine log settings such as method count, thread info visibility
     */
    private Settings settings;

    public Printer(Settings settings)
    {
        this.settings = settings;
    }

    @Override
    public IPrinter t(String tag)
    {
        settings.tag(tag);
        return this;
    }

    @Override
    public void d(String message, Object... args)
    {
        log(DEBUG, null, message, args);
    }

//    @Override
//    public void d(Object object)
//    {
//        String message;
//        if(object.getClass().isArray())
//        {
//            message = Arrays.deepToString((Object[]) object);
//        }
//        else
//        {
//            message = object.toString();
//        }
//        log(DEBUG, null, message);
//    }

    @Override
    public void e(String message, Object... args)
    {
        e(null, message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args)
    {
        log(ERROR, throwable, message, args);
    }

    @Override
    public void w(String message, Object... args)
    {
        log(WARN, null, message, args);
    }

    @Override
    public void i(String message, Object... args)
    {
        log(INFO, null, message, args);
    }

    @Override
    public void v(String message, Object... args)
    {
        log(VERBOSE, null, message, args);
    }

    @Override
    public void wtf(String message, Object... args)
    {
        log(ASSERT, null, message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    @Override
    public void json(String json)
    {
        if(TextUtils.isEmpty(json))
        {
            d("Empty/Null json content");
            return;
        }
        try
        {
            json = json.trim();
            if(json.startsWith("{"))
            {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(message);
                return;
            }
            if(json.startsWith("["))
            {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(message);
                return;
            }
            e("Invalid Json");
        } catch(JSONException e)
        {
            e("Invalid Json");
        }
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    @Override
    public void xml(String xml)
    {
        if(TextUtils.isEmpty(xml))
        {
            d("Empty/Null xml content");
            return;
        }
        try
        {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch(TransformerException e)
        {
            e("Invalid xml");
        }
    }

    @Override
    public synchronized void log(@Priority int priority, String tag, String message,
                                 Throwable throwable)
    {
        if(settings.getLogLevel() == LogLevel.NONE)
        {
            return;
        }

        if(throwable != null)
        {
            if(message != null)
            {
                message += " : ";
            }
            else
            {
                message = "";
            }

            message += Helper.getStackTraceString(throwable);
        }

        if(TextUtils.isEmpty(message))
        {
            message = "Empty/NULL log message";
        }

        if(settings.isJustShowMessage())
        {
            logChunk(priority, tag, message);
            return;
        }

        logTopBorder(priority, tag);
        logMessage(priority, tag, getThreadInfo() + message);
        logMethodCountInfo(priority, tag, getMethodCount());

        if(settings.isShowBottomLogBorder())
        {
            logBottomBorder(priority, tag);
        }
    }

    private void logMessage(int priority, String tag, String message)
    {
        //get bytes of message with system's default charset (which is UTF-8 for Android)
        byte[] bytes = message.getBytes();
        int length = bytes.length;
        if(length <= CHUNK_SIZE)
        {
            logContent(priority, tag, message);
            return;
        }

        for(int i = 0; i < length; i += CHUNK_SIZE)
        {
            int count = Math.min(length - i, CHUNK_SIZE);
            //create a new String with system's default charset (which is UTF-8 for Android)
            logContent(priority, tag, new String(bytes, i, count));
        }
    }

    private void logMethodCountInfo(int priority, String tag, int methodCount)
    {
        if(methodCount <= 0)
        {
            return;
        }

        logDivider(priority, tag);

        String spaces = "";
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        int stackOffset = getStackOffset(trace) + settings.getMethodOffset();
        for(int i = methodCount; i > 0; i--)
        {
            int stackIndex = i + stackOffset;
            if(stackIndex >= trace.length)
            {
                continue;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("║ ")
                   .append(spaces)
                   .append(getSimpleClassName(trace[stackIndex].getClassName()))
                   .append(".")
                   .append(trace[stackIndex].getMethodName())
                   .append(" ")
                   .append(" (")
                   .append(trace[stackIndex].getFileName())
                   .append(":")
                   .append(trace[stackIndex].getLineNumber())
                   .append(")");
            logChunk(priority, tag, builder.toString());
            spaces += "   ";
        }
    }

    private String getThreadInfo()
    {
        if(settings.isShowThreadInfo())
        {
            return "[\"" + Thread.currentThread().getName() + "\" Thread] :";
        }
        else
        {
            return "";
        }
    }

    @Override
    public void prettyLog(@Priority int priority, String tag, String message)
    {
        if(settings.getLogLevel() == LogLevel.NONE)
        {
            return;
        }

        if(settings.isJustShowMessage())
        {
            logChunk(priority, tag, message);
            return;
        }

        logTopBorder(priority, tag);
        logMessage(priority, tag, message);
        logMethodCountInfo(priority, tag, getMethodCount());

        if(settings.isShowBottomLogBorder())
        {
            logBottomBorder(priority, tag);
        }
    }

    @Override
    public void v()
    {
        prettyLog(VERBOSE, getTag(), createMessage());
    }

    /**
     * return message for no argument Logcat
     *
     * @return message
     */
    private String createMessage()
    {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        int stackOffset = getStackOffset(trace) + settings.getMethodOffset();
        int stackIndex = 1 + stackOffset;

        return getSimpleClassName(trace[stackIndex].getClassName()) + "." + trace[stackIndex]
                .getMethodName() + "()";
    }

    @Override
    public void d()
    {
        prettyLog(DEBUG, getTag(), createMessage());
    }

    @Override
    public void i()
    {
        prettyLog(INFO, getTag(), createMessage());
    }

    @Override
    public void w()
    {
        prettyLog(WARN, getTag(), createMessage());
    }

    @Override
    public void e()
    {
        prettyLog(ERROR, getTag(), createMessage());
    }

    @Override
    public void wtf()
    {
        prettyLog(ASSERT, getTag(), createMessage());
    }

    private void objectsLog(@Priority int priority, Object... objects)
    {
        log(priority, null, "", objects);
    }

    @Override
    public void v(Object... objects)
    {
        objectsLog(VERBOSE, objects);
    }


    @Override
    public void d(Object... objects)
    {
        objectsLog(DEBUG, objects);
    }

    @Override
    public void i(Object... objects)
    {
        objectsLog(INFO, objects);
    }

    @Override
    public void w(Object... objects)
    {
        objectsLog(WARN, objects);
    }

    @Override
    public void e(Object... objects)
    {
        objectsLog(ERROR, objects);
    }

    @Override
    public void wtf(Object... objects)
    {
        objectsLog(ASSERT, objects);
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    private synchronized void log(int priority, Throwable throwable, String msg, Object... args)
    {
        if(settings.getLogLevel() == LogLevel.NONE)
        {
            return;
        }
        String tag = getTag();
        String message = createMessage(msg, args);
        log(priority, tag, message, throwable);
    }

    private void logTopBorder(int logType, String tag)
    {
        logChunk(logType, tag,
                settings.isShowBottomLogBorder() ? TOP_BORDER :
                        needConnectBorder() ? TOP_CONNECT_BORDER : TOP_BORDER);
    }

    private boolean needConnectBorder()
    {
        long timeMillis = System.currentTimeMillis();
        boolean need = timeMillis - lastTimeMillis < CONNECT_BORDER_INTERVAL;
        lastTimeMillis = timeMillis;
        return need;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private void logHeaderContent(int logType, String tag, int methodCount)
    {
        if(settings.isShowThreadInfo())
        {
            logChunk(logType, tag, HORIZONTAL_DOUBLE_LINE + " :" + Thread.currentThread()
                                                                         .getName() + "\" Thread: ");
            logDivider(logType, tag);
        }

        logMethodCountInfo(logType, tag, methodCount);
    }

    private void logBottomBorder(int logType, String tag)
    {
        logChunk(logType, tag, BOTTOM_BORDER);
    }

    private void logDivider(int logType, String tag)
    {
        logChunk(logType, tag, MIDDLE_BORDER);
    }

    private void logContent(int logType, String tag, String chunk)
    {
        String[] lines = chunk.split(System.getProperty("line.separator"));
        for(String line : lines)
        {
            logChunk(logType, tag, HORIZONTAL_DOUBLE_LINE + " " + line);
        }
    }

    private void logChunk(@Priority int priority, String tag, String chunk)
    {
        switch(priority)
        {
            case ERROR:
                settings.getLogAdapter().e(tag, chunk);
                break;
            case INFO:
                settings.getLogAdapter().i(tag, chunk);
                break;
            case VERBOSE:
                settings.getLogAdapter().v(tag, chunk);
                break;
            case WARN:
                settings.getLogAdapter().w(tag, chunk);
                break;
            case ASSERT:
                settings.getLogAdapter().wtf(tag, chunk);
                break;
            case DEBUG:
                // Fall through, log debug by default
            default:
                settings.getLogAdapter().d(tag, chunk);
                break;
        }
    }

    private String getSimpleClassName(String name)
    {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    /**
     * @return the appropriate tag based on local or global
     */
    private String getTag()
    {
        String tag = localTag.get();
        if(tag != null)
        {
            localTag.remove();
            return tag;
        }
        return settings.getTag();
    }

    private String createMessage(String message, Object... args)
    {
        if(args == null || args.length == 0)
        {
            return message;
        }

        StringBuffer msgBuffer = new StringBuffer(message + "  ");
        for(Object arg : args)
        {
            msgBuffer.append(arg);
            msgBuffer.append(" ");
        }

        return msgBuffer.toString();
    }

    private int getMethodCount()
    {
        Integer count = localMethodCount.get();
        int result = settings.getMethodCount();
        if(count != null)
        {
            localMethodCount.remove();
            result = count;
        }
        if(result < 0)
        {
            throw new IllegalStateException("methodCount cannot be negative");
        }
        return result;
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace the stack trace
     * @return the stack offset
     */
    private int getStackOffset(StackTraceElement[] trace)
    {
        for(int i = MIN_STACK_OFFSET; i < trace.length; i++)
        {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if(!name.equals(Printer.class.getName()) && !name.equals(L.class.getName()))
            {
                return --i;
            }
        }
        return -1;
    }

}
