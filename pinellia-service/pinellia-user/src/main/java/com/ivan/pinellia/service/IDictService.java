package com.ivan.pinellia.service;

import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.mybatis.base.BaseService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface IDictService extends BaseService<Dict> {

    /**
     * 获取字典表对应中文
     *
     * @param code    编码
     * @param dictKey 字典key
     * @return: java.lang.String
     * @author: Ivan Chen
     * @date: 2020/1/12 1:15 下午
     */
    String getValue(String code, Integer dictKey);

    /**
     *
     * 测试事务
     * @param :
     * @return void
     * @author chenyifan
     * @date 2020/3/14 10:47 上午
     */
    void testTransaction();
}
