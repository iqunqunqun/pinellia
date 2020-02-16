package com.ivan.pinellia.wrapper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.mybatis.support.BaseEntityWrapper;
import com.ivan.pinellia.service.IDeptService;
import com.ivan.pinellia.tool.constant.CommonConstant;
import com.ivan.pinellia.tool.node.ForestNodeMerger;
import com.ivan.pinellia.tool.node.INode;
import com.ivan.pinellia.tool.utils.BeanUtil;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.tool.utils.SpringUtil;
import com.ivan.pinellia.vo.DeptVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class DeptWrapper extends BaseEntityWrapper<Dept, DeptVO> {

	private static IDeptService deptService;

	static {
		deptService = SpringUtil.getBean(IDeptService.class);
	}

	public static DeptWrapper build() {
		return new DeptWrapper();
	}

	@Override
	public DeptVO entityVO(Dept dept) {
		DeptVO deptVO = BeanUtil.copy(dept, DeptVO.class);
		if (ObjectUtil.equal(dept.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			deptVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = deptService.getById(dept.getParentId());
			deptVO.setParentName(parent.getDeptName());
		}
		return deptVO;
	}

	public List<INode> listNodeVO(List<Dept> list) {
		List<INode> collect = list.stream().map(dept -> BeanUtil.copy(dept, DeptVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
