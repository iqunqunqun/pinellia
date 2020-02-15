package com.ivan.pinellia.mybatis.support;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.tool.utils.NumberUtil;

import java.util.Map;

/**
 * @author chenyf
 * @program: pinellia
 * @description:
 * @create 2020-01-12 3:12 下午
 */
public class Condition {
    /**
     * 转化成mybatis plus中的Page
     *
     * @param query 查询包装类
     * @return Page T
     */
    public static <T> IPage<T> getPage(Query query) {
        Page<T> page = new Page<>(Func.toInt(query.getCurrent(), 1), Func.toInt(query.getSize(), 10));
        page.setAsc(StrUtil.splitToArray(SqlKeyword.filter(query.getAscs()), ','));
        page.setDesc(StrUtil.splitToArray(SqlKeyword.filter(query.getDescs()), ','));
        return page;
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param entity 实体类
     * @param <T>    泛型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

}
