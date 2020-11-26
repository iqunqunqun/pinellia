package com.ivan.pinellia.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p></p>
 *
 * @author ivan
 * @className ApprovalFlowVO
 * @since 2020/11/26 17:56
 */
@ApiModel("审批节点")
@Data
@EqualsAndHashCode(callSuper = true)
public class ApprovalFlowVO extends ApprovalFlow {

    /**
     * 审批人名称
     */
    @ApiModelProperty("审批人名称")
    private String approverStaffName;

    /**
     * 节点是否可用
     */
    @ApiModelProperty("节点是否可用")
    private Boolean isAvailable;
}
