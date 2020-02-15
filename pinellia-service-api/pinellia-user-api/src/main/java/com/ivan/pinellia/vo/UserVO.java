package com.ivan.pinellia.vo;

import com.ivan.pinellia.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 用户返回对象
 * @create 2020-01-05 3:10 下午
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVO extends User {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 部门名
     */
    private String deptName;

    /**
     * 性别
     */
    private String sexName;



}
