package com.ivan.pinellia.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p></p>
 *
 * @author ivan
 * @className UserDTO
 * @since 2020/7/23 22:33
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @ApiModelProperty(value = "随机盐")
    @TableField("salt")
    private String salt;

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

    private CopyOnWriteArrayList<String> arrayList;

}
