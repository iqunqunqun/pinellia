package com.ivan.pinellia.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ivan.pinellia.dto.MenuDTO;
import com.ivan.pinellia.entity.Menu;
import com.ivan.pinellia.entity.RoleMenu;
import com.ivan.pinellia.mapper.MenuMapper;
import com.ivan.pinellia.service.IMenuService;
import com.ivan.pinellia.mybatis.base.BaseServiceImpl;
import com.ivan.pinellia.service.IRoleMenuService;
import com.ivan.pinellia.tool.node.ForestNodeMerger;
import com.ivan.pinellia.tool.support.Kv;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.vo.MenuVO;
import com.ivan.pinellia.wrapper.MenuWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public IPage<MenuVO> selectMenuPage(IPage<MenuVO> page, MenuVO menu) {
        return page.setRecords(baseMapper.selectMenuPage(page, menu));
    }

    @Override
    public List<MenuVO> routes(String roleId) {
        List<Menu> allMenus = baseMapper.allMenu();
        List<Menu> roleMenus = baseMapper.roleMenu(Func.toIntList(roleId));
        List<Menu> routes = new LinkedList<>(roleMenus);
        roleMenus.forEach(roleMenu -> recursion(allMenus, routes, roleMenu));
        routes.sort(Comparator.comparing(Menu::getSort));
        MenuWrapper menuWrapper = new MenuWrapper();
        List<Menu> collect = routes.stream().filter(x -> ObjectUtil.equal(x.getCategory(), 1)).collect(Collectors.toList());
        return menuWrapper.listNodeVO(collect);
    }

    public void recursion(List<Menu> allMenus, List<Menu> routes, Menu roleMenu) {
        Optional<Menu> menu = allMenus.stream().filter(x -> ObjectUtil.equal(x.getMenuId(), roleMenu.getParentId())).findFirst();
        if (menu.isPresent() && !routes.contains(menu.get())) {
            routes.add(menu.get());
            recursion(allMenus, routes, menu.get());
        }
    }

    @Override
    public List<MenuVO> buttons(String roleId) {
        List<Menu> buttons = baseMapper.buttons(Func.toIntList(roleId));
        MenuWrapper menuWrapper = new MenuWrapper();
        return menuWrapper.listNodeVO(buttons);
    }

    @Override
    public List<MenuVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

    @Override
    public List<MenuVO> grantTree(Integer roleId) {
        return ForestNodeMerger.merge(baseMapper.grantTreeByRole(Func.toIntList(roleId.toString())));
    }

    @Override
    public List<String> roleTreeKeys(String roleIds) {
        List<RoleMenu> roleMenus = roleMenuService.list(Wrappers.<RoleMenu>query().lambda().in(RoleMenu::getRoleId, Func.toIntList(roleIds)));
        return roleMenus.stream().map(roleMenu -> Func.toStr(roleMenu.getMenuId(), "")).collect(Collectors.toList());
    }

    @Override
    public List<Kv> authRoutes(String roleId) {
        if (StrUtil.isEmpty(roleId)) {
            return null;
        }
        List<MenuDTO> routes = baseMapper.authRoutes(Func.toIntList(roleId));
        List<Kv> list = new ArrayList<>();
        routes.forEach(route -> list.add(Kv.init().set(route.getPath(), Kv.init().set("authority", Func.toStringArray(route.getAlias())))));
        return list;
    }
}
