package com.ivan.pinellia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;


/**
 * <p></p>
 *
 * @author ivan
 * @className FlowMessage
 * @since 2020/11/26 17:51
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class FlowMessage {

    /**
     * 模板
     */
    private BpmnModel bpmnModel;

    /**
     * 流程
     */
    private Process process;

    /**
     * 部署信息
     */
    private Deployment deployment;

    /**
     * 流程实例
     */
    private ProcessInstance processInstance;

}
