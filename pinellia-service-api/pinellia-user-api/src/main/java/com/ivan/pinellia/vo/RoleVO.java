package com.ivan.pinellia.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.tool.node.INode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 角色视图对象
 * @create 2019-12-29 10:59 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RoleVO对象", description = "RoleVO对象")
public class RoleVO extends Role implements INode {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;


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
     * 上级角色
     */
    private String parentName;
}
