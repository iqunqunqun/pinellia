package com.ivan.pinellia.mybatis.support;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 关键字
 * @create 2020-01-12 3:15 下午
 */

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivan.pinellia.tool.utils.StringPool;

import java.util.Map;

/**
 * 定义常用的 sql关键字
 *
 * @author Chill
 */
public class SqlKeyword {
    private final static String SQL_REGEX = "'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql";

    private static final String EQUAL = "_equal";
    private static final String NOT_EQUAL = "_notequal";
    private static final String LIKE = "_like";
    private static final String NOT_LIKE = "_notlike";
    private static final String GT = "_gt";
    private static final String LT = "_lt";
    private static final String DATE_GT = "_dategt";
    private static final String DATE_EQUAL = "_dateequal";
    private static final String DATE_LT = "_datelt";
    private static final String IS_NULL = "_null";
    private static final String NOT_NULL = "_notnull";
    private static final String IGNORE = "_ignore";


    /**
     * 把SQL关键字替换为空字符串
     *
     * @param param 关键字
     * @return string
     */
    public static String filter(String param) {
        if (param == null) {
            return null;
        }
        return param.replaceAll("(?i)" + SQL_REGEX, StringPool.EMPTY);
    }

}
