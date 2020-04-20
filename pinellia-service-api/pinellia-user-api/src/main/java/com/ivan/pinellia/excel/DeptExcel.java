package com.ivan.pinellia.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.ivan.pinellia.entity.Dept;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>部门Excel对象</p>
 *
 * @author user
 * @className DeptExcel
 * @since 2020/2/16 5:18 下午
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class DeptExcel {

    /**
     * 部门名
     */
    @ExcelProperty({"部门名"})
    private String deptName;

    /**
     * 部门全称
     */
    @ExcelProperty({"部门全称"})
    private String fullName;


    /**
     * 备注
     */
    @ExcelProperty({"备注"})
    private String remark;

}
