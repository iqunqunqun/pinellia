package com.ivan.pinellia.tool.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 节点基类
 * @create 2019-12-29 10:48 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseNode implements INode {

    /**
     * 主键ID
     */
    protected Integer id;

    /**
     * 父节点ID
     */
    protected Integer parentId;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<INode> children = new ArrayList<>();
}

