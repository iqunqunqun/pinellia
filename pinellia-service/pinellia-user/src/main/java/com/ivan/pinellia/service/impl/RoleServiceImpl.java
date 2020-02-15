package com.ivan.pinellia.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.entity.RoleMenu;
import com.ivan.pinellia.mapper.RoleMapper;
import com.ivan.pinellia.service.IRoleMenuService;
import com.ivan.pinellia.service.IRoleService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.tool.node.ForestNodeMerger;
import com.ivan.pinellia.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<RoleVO> tree() {
        String excludeRole = null;
/*        if (!CollectionUtil.contains(StrUtil.splitToArray(), RoleConstant.ADMIN)) {
            excludeRole = RoleConstant.ADMIN;
        }*/
        return ForestNodeMerger.merge(baseMapper.tree(excludeRole));
    }

    @Override
    public boolean grant(@NotEmpty List<Integer> roleIds, @NotEmpty List<Integer> menuIds) {
        // 删除角色配置的菜单集合
        roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));
        // 组装配置
        List<RoleMenu> roleMenus = new ArrayList<>();
        roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenus.add(roleMenu);
        }));
        // 新增配置
        return roleMenuService.saveBatch(roleMenus);
    }
}
