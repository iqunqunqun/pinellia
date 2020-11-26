package com.ivan.pinellia.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author ivan
 * @className ApprovalFlow
 * @since 2020/11/26 17:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApprovalFlow implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */

    @TableId(value = "flow_id", type = IdType.AUTO)
    private Integer flowId;

    /**
     * 审批模板ID
     */
    @ApiModelProperty("审批模板ID")
    private Integer fid;

    /**
     * 关联条件表（默认为0，如果某条件下的节点则关联条件ID）
     */
    @ApiModelProperty("关联条件表（默认为0，如果某条件下的节点则关联条件ID）")
    private Integer conditionId;

    /**
     * 审批人ID
     */
    @ApiModelProperty("审批人ID")
    private Integer approverStaffId;

    /**
     * 审批人类型（0-指定人，1-1级上级，2-2级上级）
     */
    @ApiModelProperty("审批人类型（0-指定人，1-1级上级，2-2级上级）")
    private Integer approverType;

    /**
     * 审批人状态（0-审批，1-抄送）
     */
    @ApiModelProperty("审批人状态（0-审批，1-抄送）")
    private Integer approverStatus;

    /**
     * 审批节点序号
     */
    @ApiModelProperty("审批节点序号")
    private Integer nodeNumber;

    /**
     * 节点类型（0.顺序,1.会签节点,2.或签节点）
     */
    @ApiModelProperty("节点类型（0.顺序,1.会签节点,2.或签节点）")
    private Integer nodeType;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    public static final String FLOW_ID = "flow_id";

    public static final String FID = "fid";

    public static final String CONDITION_ID = "condition_id";

    public static final String APPROVER_STAFF_ID = "approver_staff_id";

    public static final String APPROVER_TYPE = "approver_type";

    public static final String APPROVER_STATUS = "approver_status";

    public static final String NODE_NUMBER = "node_number";

    public static final String NODE_TYPE = "node_type";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}

