package com.ivan.pinellia.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.service.IDeptService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.node.INode;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.vo.DeptVO;
import com.ivan.pinellia.vo.UserVO;
import com.ivan.pinellia.wrapper.DeptWrapper;
import com.ivan.pinellia.wrapper.UserWrapper;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "部门", tags = "部门")
public class DeptController {
    private IDeptService deptService;

    /**
     * 详情
     */
    @GetMapping("/detail/{id}")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入dept")
    public R<DeptVO> detail(@PathVariable("id") Integer id) {
        Dept dept = deptService.getById(id);
        return R.data(DeptWrapper.build().entityVO(dept));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "fullName", value = "部门全称", paramType = "query", dataType = "string")
    })
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "列表", notes = "传入dept")
    public R<List<INode>> list() {
        List<Dept> list = this.deptService.list();
        return R.data(DeptWrapper.build().listNodeVO(list));
    }

    /**
     * 获取部门树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<DeptVO>> tree() {
        List<DeptVO> tree = deptService.tree();
        return R.data(tree);
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增或修改", notes = "传入dept")
    public R submit(@Valid @RequestBody Dept dept) {
        return R.status(deptService.saveOrUpdate(dept));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(deptService.removeByIds(Func.toIntList(ids)));
    }


    /**
     * 查询部门的组织架构
     */
    @ApiOperation("查询部门的组织架构")
    @ApiOperationSupport(order = 6)
    @GetMapping("/organization")
    public R<List<DeptVO>> organization() {
        List<DeptVO> deptList = deptService.organization();
        return R.data(deptList);
    }


    /**
     * 根据部门ID查询员工
     */
    @ApiOperation("根据部门ID查询员工")
    @ApiOperationSupport(order = 7)
    @GetMapping("/allStaff")
    public R allStaff(@RequestParam("deptId") Integer deptId) {
        List<User> users = this.deptService.allDept(deptId);
        return R.data(UserWrapper.build().listVO(users));
    }

    @ApiOperation("測試Excel")
    @ApiOperationSupport(order = 7)
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        this.deptService.exportExcel(response);
    }

    @ApiOperation("导入Excel")
    @ApiOperationSupport(order = 8)
    @PostMapping("/importExcel")
    public R importExcel(@RequestPart MultipartFile file) {
        boolean b = this.deptService.importExcel(file);
        return R.data(b);
    }

    @ApiOperation("测试事务控制")
    @ApiOperationSupport(order = 9)
    @PostMapping("/testTran")
    public R testTran() {
        boolean b = this.deptService.testTran();
        return R.data(b);
    }
}

