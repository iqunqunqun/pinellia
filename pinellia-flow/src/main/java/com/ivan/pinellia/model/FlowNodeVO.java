package com.ivan.pinellia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p></p>
 *
 * @author ivan
 * @className FlowNodeVO
 * @since 2020/11/26 17:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowNodeVO {

    /**
     * 节点编号
     */
    private Integer nodeIndex;

    /**
     * 节点类型
     */
    private Integer nodeType;

    /**
     * 节点变量
     */
    private String nodeVar;

    /**
     * 节点列表
     */
    private List<ApprovalFlowVO> nodeList;

    public static FlowNodeVO init(Integer nodeIndex, Integer nodeType, List<ApprovalFlowVO> nodeList) {
        FlowNodeVO nodeVO = new FlowNodeVO();
        nodeVO.setNodeIndex(nodeIndex);
        nodeVO.setNodeType(nodeType);
        nodeVO.setNodeList(nodeList);
        return nodeVO;
    }
}

