package com.ivan.pinellia.mapper;

import com.ivan.pinellia.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ivan.pinellia.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取树形节点
     *
     * @param excludeRole
     * @return
     */
    List<RoleVO> tree(@Param("excludeRole") String excludeRole);
}
