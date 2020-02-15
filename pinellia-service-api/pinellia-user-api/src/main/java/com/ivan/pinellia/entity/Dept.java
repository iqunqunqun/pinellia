package com.ivan.pinellia.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ivan.pinellia.mybatis.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pinellia_dept")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 父主键
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 部门名
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 部门全称
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;
}
