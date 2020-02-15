package com.ivan.pinellia.tool.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;



/**
 * @author chenyf
 * @program: pinellia
 * @description: 字符串工具类
 * @create 2020-01-12 11:33 上午
 */
public class Func {

    /**
     * 转换为Integer集合<br>
     *
     * @param str 结果被转换的值
     * @return 结果
     */
    public static List<Integer> toIntList(String str) {
        return Arrays.asList(toIntegerArray(str));
    }

    /**
     * 转换为Integer集合<br>
     *
     * @param split 分隔符
     * @param str   被转换的值
     * @return 结果
     */
    public static List<Integer> toIntList(String str, String split) {
        return Arrays.asList(toIntegerArray(str, split));
    }

    /**
     * 转换为Integer数组<br>
     *
     * @param str 被转换的值
     * @return 结果
     */
    public static Integer[] toIntegerArray(String str) {
        return toIntegerArray(str, ",");
    }

    /**
     * 转换为Integer数组<br>
     *
     * @param split 分隔符
     * @param str   被转换的值
     * @return 结果
     */
    public static Integer[] toIntegerArray(String str, String split) {
        if (StrUtil.isEmpty(str)) {
            return new Integer[]{};
        }
        String[] arr = str.split(split);
        return Convert.toIntArray(arr);
    }

    /**
     * 强转string,并去掉多余空格
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return String
     */
    public static String toStr(Object str, String defaultValue) {
        if (null == str) {
            return defaultValue;
        }
        return String.valueOf(str);
    }


    /**
     * <p>Convert a <code>String</code> to an <code>int</code>, returning a
     * default value if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, the default value is returned.</p>
     *
     * <pre>
     *   $.toInt(null, 1) = 1
     *   $.toInt("", 1)   = 1
     *   $.toInt("1", 0)  = 1
     * </pre>
     *
     * @param value        the string to convert, may be null
     * @param defaultValue the default value
     * @return the int represented by the string, or the default if conversion fails
     */
    public static int toInt(final Object value, final int defaultValue) {
        return NumberUtil.toInt(String.valueOf(value), defaultValue);
    }

    /**
     * 转换为String集合<br>
     *
     * @param str 结果被转换的值
     * @return 结果
     */
    public static List<String> toStrList(String str) {
        return Arrays.asList(toStringArray(str));
    }

    /**
     * 转换为String数组<br>
     *
     * @param split 分隔符
     * @param str   被转换的值
     * @return 结果
     */
    public static String[] toStringArray(String split, String str) {
        if (StrUtil.isBlank(str)) {
            return new String[]{};
        }
        return str.split(split);
    }

    /**
     * 转换为String数组<br>
     *
     * @param str 被转换的值
     * @return 结果
     */
    public static String[] toStringArray(String str) {
        return toStringArray(str, ",");
    }


}
