package com.ivan.pinellia.wrapper;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.ivan.pinellia.entity.Menu;
import com.ivan.pinellia.feign.IUserClient;
import com.ivan.pinellia.mybatis.support.BaseEntityWrapper;
import com.ivan.pinellia.service.IMenuService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.constant.CommonConstant;
import com.ivan.pinellia.tool.node.ForestNodeMerger;
import com.ivan.pinellia.tool.utils.BeanUtil;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.tool.utils.SpringUtil;
import com.ivan.pinellia.vo.MenuVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 菜单包装类
 * @create 2020-01-12 5:14 下午
 */
public class MenuWrapper extends BaseEntityWrapper<Menu, MenuVO> {

    private static IMenuService menuService;

    private static IUserClient dictClient;

    static {
        menuService = SpringUtil.getBean(IMenuService.class);
        dictClient = SpringUtil.getBean(IUserClient.class);
    }

    public static MenuWrapper build() {
        return new MenuWrapper();
    }

    @Override
    public MenuVO entityVO(Menu menu) {
        MenuVO menuVO = BeanUtil.copy(menu, MenuVO.class);
        if (ObjectUtil.equal(menu.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            menuVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            Menu parent = menuService.getById(menu.getParentId());
            menuVO.setParentName(parent.getName());
        }
        R<String> d1 = dictClient.getValue("menu_category", Func.toInt(menuVO.getCategory(), -1));
        R<String> d2 = dictClient.getValue("button_func", Func.toInt(menuVO.getAction(), -1));
        R<String> d3 = dictClient.getValue("yes_no", Func.toInt(menuVO.getIsOpen(), -1));
        if (d1.isSuccess()) {
            menuVO.setCategoryName(d1.getData());
        }
        if (d2.isSuccess()) {
            menuVO.setActionName(d2.getData());
        }
        if (d3.isSuccess()) {
            menuVO.setIsOpenName(d3.getData());
        }
        return menuVO;
    }


    public List<MenuVO> listNodeVO(List<Menu> list) {

        List<MenuVO> collect = Lists.newArrayList();

        list.forEach(menu -> {
            MenuVO menuVO = BeanUtil.copy(menu, MenuVO.class);
            menuVO.setId(menu.getMenuId());
            collect.add(menuVO);
        });

        return ForestNodeMerger.merge(collect);
    }

}

