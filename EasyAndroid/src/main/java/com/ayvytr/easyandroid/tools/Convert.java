package com.ayvytr.easyandroid.tools;

import java.util.Arrays;

/**
 * 类型转换类，包含从大部分基本类型到 boolean, int , byte的转换等.
 * <p>
 * 涉及浮点数是否相等的运算，精度默认按照 {@link Convert#DOUBLE_ACCURACY}(0.00001).
 * <p>
 * 当前版本只提供了 toInt, toBool, toByte, isZero 一类方法，其他进制转换等后续根据情况决定是否添加。
 * <p>
 * 这个类参照 C#的Convert类设计了部分方法，有关SDK见：
 * <a href="https://msdn.microsoft.com/zh-cn/library/system.convert(v=vs.110).aspx" target="_blank">C# Convert</a>
 * </p>
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.5.0
 */

public class Convert
{
    //浮点数是否相等的精度
    public static final double DOUBLE_ACCURACY = 0.00001;

    /**
     * value转boolean
     *
     * @param value int
     * @return value == 0: false; value != 0: true
     */
    public static boolean toBool(int value)
    {
        return value == 0;
    }

    /**
     * value转boolean
     *
     * @param value short
     * @return value == 0: false; value != 0: true
     */
    public static boolean toBool(short value)
    {
        return toBool((int) value);
    }

    /**
     * value转boolean
     *
     * @param value byte
     * @return value == 0: false; value != 0: true
     */
    public static boolean toBool(byte value)
    {
        return toBool((int) value);
    }

    /**
     * value转boolean
     *
     * @param value char
     * @return value == 0: false; value != 0: true
     */
    public static boolean toBool(char value)
    {
        return toBool((int) value);
    }

    /**
     * value转boolean
     *
     * @param value String
     * @return value.equals(true) ?  true : false
     */
    public static boolean toBool(String value)
    {
        if(TextTool.isEmpty(value))
        {
            return false;
        }

        if(value.equalsIgnoreCase("true"))
        {
            return true;
        }

        return false;
    }

    /**
     * value转boolean
     *
     * @param value long
     * @return value == 0: false; value != 0: true
     */
    public static boolean toBool(long value)
    {
        return value == 0;
    }

    /**
     * value转boolean
     *
     * @param value float
     * @return value == 0: false; value != 0: true
     */
    public static boolean toBool(float value)
    {
        return toBool((double) value);
    }

    /**
     * value转boolean
     *
     * @param value double
     * @return value == 0: false; value != 0: true
     * @see Convert#DOUBLE_ACCURACY
     */
    public static boolean toBool(double value)
    {
        return isZero(value);
    }

    /**
     * 判断value是不是0
     *
     * @param value int
     * @return value == 0: true; false
     */
    public static boolean isZero(int value)
    {
        return value == 0;
    }

    /**
     * 判断value是不是0
     *
     * @param value short
     * @return value == 0: true; false
     */
    public static boolean isZero(short value)
    {
        return value == 0;
    }

    /**
     * 判断value是不是0
     *
     * @param value long
     * @return value == 0: true; false
     */
    public static boolean isZero(long value)
    {
        return value == 0;
    }

    /**
     * 判断value是不是0
     *
     * @param value byte
     * @return value == 0: true; false
     */
    public static boolean isZero(byte value)
    {
        return isZero((int) value);
    }

    /**
     * 判断value是不是0
     *
     * @param value char
     * @return value == 0: true; false
     */
    public static boolean isZero(char value)
    {
        return isZero((int) value);
    }

    /**
     * 判断value是不是0
     *
     * @param value String
     * @return {@code value.equals('0')}: true; false
     */
    public static boolean isZero(String value)
    {
        return !TextTool.isEmpty(value) && value.equals("0");

    }

    /**
     * 判断value是不是0
     *
     * @param value double
     * @return value == 0: true; false
     * @see Convert#DOUBLE_ACCURACY
     */
    public static boolean isZero(double value)
    {
        return value < DOUBLE_ACCURACY && value > -DOUBLE_ACCURACY;
    }

    /**
     * 判断value是不是0
     *
     * @param value double
     * @return value == 0: true; false
     * @see Convert#DOUBLE_ACCURACY
     */
    public static boolean isZero(float value)
    {
        return isZero((double) value);
    }

    /**
     * value转换返回为int
     *
     * @param value boolean
     * @return int
     */
    public static int toInt(boolean value)
    {
        return value ? 1 : 0;
    }


    /**
     * value转换返回为int
     *
     * @param value short
     * @return int
     */
    public static int toInt(short value)
    {
        return value;
    }

    /**
     * value转换返回为int
     *
     * @param value long
     * @return int
     */
    public static int toInt(long value)
    {
        return (int) value;
    }

    /**
     * value转换返回为int
     *
     * @param value byte
     * @return int
     */
    public static int toInt(byte value)
    {
        return toInt((int) value);
    }

    /**
     * value转换返回为int
     *
     * @param value char
     * @return int
     */
    public static int toInt(char value)
    {
        return toInt((int) value);
    }

    /**
     * value转换返回为int
     *
     * @param value float
     * @return int
     */
    public static int toInt(float value)
    {
        return toInt((double) value);
    }

    /**
     * value转换返回为int
     *
     * @param value double
     * @return int
     */
    public static int toInt(double value)
    {
        return (int) value;
    }

    /**
     * value转换返回为int
     *
     * @param value String
     * @return int
     */
    public static int toInt(String value)
    {
        return Integer.valueOf(value);
    }

    /**
     * value转换返回byte
     *
     * @param value boolean
     * @return byte
     */
    public static byte toByte(boolean value)
    {
        return (byte) (value ? 1 : 0);
    }

    /**
     * value转换返回byte
     *
     * @param value int
     * @return byte
     */
    public static byte toByte(int value)
    {
        return (byte) value;
    }

    /**
     * value转换返回byte
     *
     * @param value value
     * @return byte
     */
    public static byte toByte(short value)
    {
        return (byte) value;
    }

    /**
     * value转换返回byte
     *
     * @param value long
     * @return byte
     */
    public static byte toByte(long value)
    {
        return (byte) value;
    }

    /**
     * value转换返回byte
     *
     * @param value float
     * @return byte
     */
    public static byte toByte(float value)
    {
        return (byte) value;
    }

    /**
     * value转换返回byte
     *
     * @param value double
     * @return byte
     */
    public static byte toByte(double value)
    {
        return (byte) value;
    }

    /**
     * value转换返回byte
     *
     * @param value byte
     * @return byte
     */
    public static byte toByte(char value)
    {
        return (byte) value;
    }

    /**
     * value转换返回byte, value需要能先被转换成int
     *
     * @param value String
     * @return byte
     */
    public static byte toByte(String value)
    {
        return (byte) (int) Integer.valueOf(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(char value)
    {
        return Character.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(short value)
    {
        return Short.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(byte value)
    {
        return Byte.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(int value)
    {
        return Integer.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(long value)
    {
        return Long.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(float value)
    {
        return Float.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(double value)
    {
        return Double.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(boolean value)
    {
        return Boolean.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(char[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(short[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(byte[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(int[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(long[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(float[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(double[] value)
    {
        return Arrays.toString(value);
    }

    /**
     * value转换返回String
     *
     * @param value 目标值
     * @return String
     */
    public static String toString(boolean[] value)
    {
        return Arrays.toString(value);
    }

}
