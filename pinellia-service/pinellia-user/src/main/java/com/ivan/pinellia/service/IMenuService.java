package com.ivan.pinellia.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ivan.pinellia.entity.Menu;
import com.ivan.pinellia.mybatis.base.BaseService;
import com.ivan.pinellia.tool.support.Kv;
import com.ivan.pinellia.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface IMenuService extends BaseService<Menu> {

    /**
     * 自定义分页
     *
     * @param page
     * @param menu
     * @return
     */
    IPage<MenuVO> selectMenuPage(IPage<MenuVO> page, MenuVO menu);

    /**
     * 菜单树形结构
     *
     * @param roleId:
     * @return: com.ivan.pinellia.vo.MenuVO
     */
    List<MenuVO> routes(String roleId);

    /**
     * 按钮树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> buttons(String roleId);

    /**
     * 树形结构
     *
     * @return
     */
    List<MenuVO> tree();

    /**
     * 授权树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> grantTree(Integer roleId);

    /**
     * 默认选中节点
     *
     * @param roleIds
     * @return
     */
    List<String> roleTreeKeys(String roleIds);

    /**
     * 获取配置的角色权限
     *
     * @param roleId
     * @return: java.util.List<com.ivan.pinellia.tool.support.Kv>
     * @author: Ivan Chen
     * @date: 2020/1/14 10:30 下午
     */
    List<Kv> authRoutes(String roleId);
}
