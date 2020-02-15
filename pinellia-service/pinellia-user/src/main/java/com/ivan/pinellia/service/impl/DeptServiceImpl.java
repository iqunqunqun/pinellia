package com.ivan.pinellia.service.impl;

import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.mapper.DeptMapper;
import com.ivan.pinellia.service.IDeptService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

}
