package com.ivan.pinellia.tool.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenyf
 * @program: pinellia
 * @description: 森林节点
 * @create 2019-12-29 11:05 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ForestNode extends BaseNode {

    /**
     * 节点内容
     */
    private Object content;

    public ForestNode(Integer id, Integer parentId, Object content) {
        this.id = id;
        this.parentId = parentId;
        this.content = content;
    }

}
