package com.ivan.pinellia.mapper;

import com.ivan.pinellia.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ivan.pinellia.vo.DeptVO;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 获取树形节点
     *
     * @return
     */
    List<DeptVO> tree();
}
