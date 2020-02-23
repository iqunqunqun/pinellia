package com.ivan.pinellia.service;


import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mybatis.base.BaseService;
import com.ivan.pinellia.tool.api.R;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ivan
 * @since 2019-12-29
 */
public interface IUserService extends BaseService<User> {

    /**
     * 获取用户详情
     *
     * @param uid：
     * @return com.ivan.pinellia.model.entity.PinelliaUser
     */
    User getUserDetailById(Integer uid);

    /**
     * 获取角色名称
     * @param roleIds:
     * @return: java.util.List<java.lang.String>
     *
     * @author: Ivan Chen
     * @date: 2020/1/5 4:53 下午
     */
    List<String> getRoleName(String roleIds);

    /**
     * 获取部门名称
     * @param deptId:
     * @return: java.util.List<java.lang.String>
     *
     * @author: Ivan Chen
     * @date: 2020/1/5 4:55 下午
     */
    List<String> getDeptName(String deptId);

    /**
     * 新增或修改用户
     *
     * @param user
     * @return: boolean
     * @author: Ivan Chen
     * @date: 2020/1/5 8:26 下午
     */
    boolean submit(User user);

    /**
     * 给用户设置角色
     *
     * @param userIds
     * @param roleIds
     * @return
     */
    boolean grant(String userIds, String roleIds);


}
