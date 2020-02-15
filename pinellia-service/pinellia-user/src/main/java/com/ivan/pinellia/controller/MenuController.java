package com.ivan.pinellia.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivan.pinellia.entity.Menu;
import com.ivan.pinellia.mybatis.support.Condition;
import com.ivan.pinellia.service.IMenuService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.constant.RoleConstant;
import com.ivan.pinellia.tool.support.Kv;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.vo.MenuVO;
import com.ivan.pinellia.wrapper.MenuWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */

@Api(value = "菜单", tags = "菜单")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 详情
     */
    @GetMapping("/detail")
//    @PreAuth(RoleConstant.HAS_ROLE_ADMIN)
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入menu")
    public R<MenuVO> detail(Menu menu) {
        Menu detail = menuService.getOne(Condition.getQueryWrapper(menu));
        return R.data(MenuWrapper.build().entityVO(detail));
    }


    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增或修改", notes = "传入menu")
    public R submit(@Valid @RequestBody Menu menu) {
        return R.status(menuService.saveOrUpdate(menu));
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(menuService.removeByIds(Func.toIntList(ids)));
    }

    /**
     * 前端菜单数据
     */
    @GetMapping("/routes")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "前端菜单数据", notes = "前端菜单数据")
    public R<List<MenuVO>> routes(@RequestParam("id") String id) {
        List<MenuVO> list = menuService.routes(id);
        return R.data(list);
    }

    /**
     * 前端按钮数据
     */
    @GetMapping("/buttons")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "前端按钮数据", notes = "前端按钮数据")
    public R<List<MenuVO>> buttons(@RequestParam("roleId") String roleId) {
        List<MenuVO> list = menuService.buttons(roleId);
        return R.data(list);
    }

    /**
     * 获取菜单树形结构
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<MenuVO>> tree() {
        List<MenuVO> tree = menuService.tree();
        return R.data(tree);
    }

    /**
     * 获取权限分配树形结构
     */
    @GetMapping("/grant-tree")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
    public R<List<MenuVO>> grantTree(@RequestParam("roleId") Integer roleId) {
        return R.data(menuService.grantTree(roleId));
    }

    /**
     * 获取权限分配树形结构
     */
    @GetMapping("/role-tree-keys")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "角色所分配的树", notes = "角色所分配的树")
    public R<List<String>> roleTreeKeys(String roleIds) {
        return R.data(menuService.roleTreeKeys(roleIds));
    }

    /**
     * 获取配置的角色权限
     */
    @GetMapping("auth-routes")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "菜单的角色权限")
    public R<List<Kv>> authRoutes(@RequestParam("roleId") String roleId) {
        return R.data(menuService.authRoutes(roleId));
    }

}

