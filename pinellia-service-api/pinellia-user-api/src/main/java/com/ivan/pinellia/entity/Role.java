package com.ivan.pinellia.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ivan.pinellia.mybatis.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pinellia_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 父主键
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 角色名
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 角色别名
     */
    @TableField("role_alias")
    private String roleAlias;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;

}
