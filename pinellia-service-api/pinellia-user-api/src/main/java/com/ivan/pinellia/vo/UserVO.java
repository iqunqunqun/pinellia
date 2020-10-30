package com.ivan.pinellia.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ivan.pinellia.mybatis.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ivan
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="用户表")
public class UserVO extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @ApiModelProperty(value = "简介")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "部门ID")
    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty(value = "0-正常，9-锁定")
    @TableField("lock_flag")
    private String lockFlag;

    @ApiModelProperty(value = "0-正常，1-删除")
    @TableField("del_flag")
    private String delFlag;

    @ApiModelProperty("是否记住")
    private Boolean isRememberMe;

}
