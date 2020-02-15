package com.ivan.pinellia.mapper;

import com.ivan.pinellia.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface DictMapper extends BaseMapper<Dict> {


    /**
     * 获取字典表对应中文
     *
     * @param code    字典编号
     * @param dictKey 字典序号
     * @return
     */
    String getValue(@Param("code") String code, @Param("dictKey") Integer dictKey);
}
