package com.ivan.pinellia.tool.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 树形节点
 * @create 2019-12-29 10:54 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseNode {

    private String title;

    private Integer key;

    private Integer value;

}
