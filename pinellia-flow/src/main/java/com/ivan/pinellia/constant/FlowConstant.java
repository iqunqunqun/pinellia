package com.ivan.pinellia.constant;

/**
 * <p></p>
 *
 * @author ivan
 * @className FlowConstant
 * @since 2020/11/26 16:22
 */
public interface FlowConstant {

    /**
     * 下划线
     */
    String UNDERLINE = "_";

    /**
     * 开始节点
     */
    String FLOW_START_EVENT = "startEvent";

    /**
     * 结束节点
     */
    String FLOW_END_EVENT = "endEvent";

    /**
     * 默认会签条件
     */
    String FLOW_COUNTERSIGN_CONDITION = "nrOfCompletedInstances==nrOfInstances";

    /**
     * 默认或签条件
     */
    String FLOW_OR_SIGN_CONDITION = "nrOfCompletedInstances == 1";

}
