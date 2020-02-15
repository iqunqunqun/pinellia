package com.ivan.pinellia.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ivan.pinellia.mybatis.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pinellia_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 父级菜单
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单编号
     */
    @TableField("code")
    private String code;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单别名
     */
    @TableField("alias")
    private String alias;

    /**
     * 请求地址
     */
    @TableField("path")
    private String path;

    /**
     * 菜单资源
     */
    @TableField("source")
    private String source;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 菜单类型
     */
    @TableField("category")
    private Integer category;

    /**
     * 操作按钮类型
     */
    @TableField("action")
    private Integer action;

    /**
     * 是否打开新页面
     */
    @TableField("is_open")
    private Integer isOpen;

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
