package com.ivan.pinellia.service.impl;

import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.mapper.RoleMapper;
import com.ivan.pinellia.service.IRoleService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-07-26
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

}
