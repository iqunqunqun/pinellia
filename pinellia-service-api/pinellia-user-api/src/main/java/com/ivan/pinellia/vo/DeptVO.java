package com.ivan.pinellia.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.tool.node.INode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

/**
 * 视图实体类
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeptVO对象", description = "DeptVO对象")
public class DeptVO extends Dept implements INode {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 总人数
	 */
	private Integer totalPeople;

	/**
	 * 本部人数
	 */
	private Integer headquarterPeople;

	/**
	 * 其它人数
	 */
	private Integer otherPeople;


	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<INode> children;

	@Override
	public List<INode> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		return this.children;
	}

	/**
	 * 上级部门
	 */
	private String parentName;

}
