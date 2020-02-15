package com.ivan.pinellia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.pinellia.entity.RoleMenu;
import com.ivan.pinellia.mapper.RoleMenuMapper;
import com.ivan.pinellia.service.IRoleMenuService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
