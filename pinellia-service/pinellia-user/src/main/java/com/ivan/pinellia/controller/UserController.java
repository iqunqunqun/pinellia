package com.ivan.pinellia.controller;





import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.vo.UserVO;
import com.ivan.pinellia.wrapper.UserWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ivan
 * @since 2019-12-29
 */

@Api(tags = {"用户接口"})
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperationSupport(order = 1)
    @ApiOperation("获取用户详情")
    @GetMapping("/detail/{uid}")
    public R<UserVO> detail(@PathVariable("uid") Integer uid) {
        return R.data(UserWrapper.build().entityVO(this.userService.getUserDetailById(uid)));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "新增或修改", notes = "传入User")
    public R<Boolean> submit(@Valid @RequestBody User user) {
        return R.status(userService.submit(user));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "修改", notes = "传入User")
    public R update(@Valid @RequestBody User user) {
        return R.status(userService.updateById(user));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "删除", notes = "传入地基和")
    public R remove(@RequestParam String ids) {
        return R.status(userService.deleteLogic(Func.toIntList(ids)));
    }

    /**
     * 设置菜单权限
     *
     * @param userIds:
     * @param roleIds:
     * @return
     */
    @PostMapping("/grant")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
    public R grant(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds,
                   @ApiParam(value = "roleId集合", required = true) @RequestParam String roleIds) {
        boolean temp = userService.grant(userIds, roleIds);
        return R.status(temp);
    }
}

