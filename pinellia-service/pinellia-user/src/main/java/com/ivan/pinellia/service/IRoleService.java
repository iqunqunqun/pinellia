package com.ivan.pinellia.service;

import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.mybatis.base.BaseService;
import com.ivan.pinellia.vo.RoleVO;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface IRoleService extends BaseService<Role> {

    /**
     * 获取角色的树形结构
     *
     * @return: java.util.List<com.ivan.pinellia.vo.RoleVO>
     * @author: Ivan Chen
     * @date: 2020/1/12 3:44 下午
     */
    List<RoleVO> tree();

    /**
     * 权限配置
     *
     * @param roleIds 角色id集合
     * @param menuIds 菜单id集合
     * @return 是否成功
     */
    boolean grant(@NotEmpty List<Integer> roleIds, @NotEmpty List<Integer> menuIds);
}
