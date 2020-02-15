package com.ivan.pinellia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ivan.pinellia.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ivan
 * @since 2019-12-29
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取角色名称
     *
     * @param ids：
     * @return: java.util.List<java.lang.String>
     * @author: Ivan Chen
     * @date: 2020/1/5 5:02 下午
     */
    List<String> getRoleName(String[] ids);

    /**
     * 获取部门ID
     * @param ids:
     * @return: java.util.List<java.lang.String>
     *
     * @author: Ivan Chen
     * @date: 2020/1/5 5:01 下午
     */
    List<String> getDeptName(String[] ids);
}
