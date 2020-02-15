package com.ivan.pinellia.service.impl;

import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.mapper.DictMapper;
import com.ivan.pinellia.service.IDictService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.tool.utils.StringPool;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public String getValue(String code, Integer dictKey) {
        return Func.toStr(baseMapper.getValue(code, dictKey), StringPool.EMPTY);
    }
}
