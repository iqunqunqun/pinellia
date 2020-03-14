package com.ivan.pinellia.service.impl;

import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.entity.RoleMenu;
import com.ivan.pinellia.feign.IUserClient;
import com.ivan.pinellia.mapper.DictMapper;
import com.ivan.pinellia.service.IDictService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.service.IRoleMenuService;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.tool.utils.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public String getValue(String code, Integer dictKey) {
        return Func.toStr(baseMapper.getValue(code, dictKey), StringPool.EMPTY);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        boolean b = true;
        Dict dict = new Dict();
        dict.setCode("hehe");
        this.save(dict);

        RoleMenu menu = this.roleMenuService.getById(1);

        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenuId(menu.getMenuId());
        roleMenu.setRoleId(menu.getRoleId());
        this.roleMenuService.save(roleMenu);
    }
}
