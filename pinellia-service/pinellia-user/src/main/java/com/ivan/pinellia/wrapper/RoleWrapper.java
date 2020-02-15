package com.ivan.pinellia.wrapper;

import cn.hutool.core.util.ObjectUtil;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.mybatis.support.BaseEntityWrapper;
import com.ivan.pinellia.service.IRoleService;
import com.ivan.pinellia.tool.constant.CommonConstant;
import com.ivan.pinellia.tool.node.ForestNodeMerger;
import com.ivan.pinellia.tool.node.INode;
import com.ivan.pinellia.tool.utils.BeanUtil;
import com.ivan.pinellia.tool.utils.SpringUtil;
import com.ivan.pinellia.vo.RoleVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 角色包装类
 * @create 2020-01-12 3:03 下午
 */
public class RoleWrapper extends BaseEntityWrapper<Role, RoleVO> {
    private static IRoleService roleService;

    static {
        roleService = SpringUtil.getBean(IRoleService.class);
    }

    public static RoleWrapper build() {
        return new RoleWrapper();
    }

    @Override
    public RoleVO entityVO(Role role) {
        RoleVO roleVO = BeanUtil.copy(role, RoleVO.class);
        roleVO.setId(role.getRoleId());
        if (ObjectUtil.equal(role.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            roleVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            Role parent = roleService.getById(role.getParentId());
            roleVO.setParentName(parent.getRoleName());
        }

        return roleVO;
    }

    public List<INode> listNodeVO(List<Role> list) {
        List<INode> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }
}
