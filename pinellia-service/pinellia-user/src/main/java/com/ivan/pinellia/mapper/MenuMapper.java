package com.ivan.pinellia.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ivan.pinellia.dto.MenuDTO;
import com.ivan.pinellia.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ivan.pinellia.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 自定义分页
     *
     * @param page
     * @param menu
     * @return
     */
    List<MenuVO> selectMenuPage(IPage page, MenuVO menu);

    /**
     * 树形结构
     *
     * @return
     */
    List<MenuVO> tree();

    /**
     * 授权树形结构
     *
     * @return
     */
    List<MenuVO> grantTree();

    /**
     * 授权树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> grantTreeByRole(List<Integer> roleId);

    /**
     * 所有菜单
     *
     * @return
     */
    List<Menu> allMenu();

    /**
     * 权限配置菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> roleMenu(List<Integer> roleId);

    /**
     * 菜单树形结构
     *
     * @param roleId
     * @return
     */
    List<Menu> routes(List<Integer> roleId);

    /**
     * 按钮树形结构
     *
     * @param roleId
     * @return
     */
    List<Menu> buttons(List<Integer> roleId);

    /**
     * 获取配置的角色权限
     *
     * @param roleIds
     * @return: java.util.List<com.ivan.pinellia.dto.MenuDTO>
     * @author: Ivan Chen
     * @date: 2020/1/14 10:33 下午
     */
    List<MenuDTO> authRoutes(List<Integer> roleIds);
}
