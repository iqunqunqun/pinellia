package com.ivan.pinellia.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivan.pinellia.service.IDictService;
import com.ivan.pinellia.tool.api.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private IDictService dictService;

    @ApiOperation("事务测试")
    @ApiOperationSupport(order = 1)
    @PostMapping("/testTransaction")
    public void testTransaction() {
        this.dictService.testTransaction();
    }

}

