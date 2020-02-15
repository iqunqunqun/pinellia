package com.ivan.pinellia.tool.node;

import java.util.List;

/**
 * @author chenyf
 * @program: pinellia
 * @description:
 * @create 2019-12-29 10:46 上午
 */
public interface INode {

    /**
     * 主键
     *
     * @return Integer
     */
    Integer getId();

    /**
     * 父主键
     *
     * @return Integer
     */
    Integer getParentId();

    /**
     * 子孙节点
     *
     * @return List
     */
    List<INode> getChildren();

}

