package com.ivan.pinellia.service;

import com.ivan.pinellia.tool.api.R;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author ivan
 * @className IFlowCustomService
 * @since 2020/11/26 16:50
 */
public interface IFlowCustomService {
    /**
     * <p>获取当前部署的流程</p>
     * @author chenyf
     * @date 2019/9/30
     * @return java.util.List<org.activiti.engine.repository.ProcessDefinition>
     */
    List<ProcessDefinition> getProcessDefinitions();

    /**
     * <p>获取当前部署的流程</p>
     * @author chenyf
     * @date 2019/9/30
     * @param category: 流程所属类型
     * @return java.util.List<org.activiti.engine.repository.ProcessDefinition>
     */
    List<ProcessDefinition> getProcessDefinitions(String category);

    /**
     * <p>部署流程</p>
     * @author chenyf
     * @date 2019/9/30
     * @param bpmnName: bpmn文件名称（不需要文件后缀）
     * @param processName: 需设置的流程名称
     * @return com.insigma.oa.common.json.JSONResult
     */
    R deployProcess(String bpmnName, String processName);

    /**
     * <p>启动流程</p>
     * @author chenyf
     * @date 2019/9/30
     * @param processKey: process的ID，与xml文件中的ID一致
     * @param map: 流程中所需的参数图
     * @return com.insigma.oa.common.json.JSONResult
     */
    ProcessInstance startProcess(String processKey, Map<String, Object> map);


    /**
     * <p>获取流程实例的当前任务节点</p>
     * @author chenyf
     * @date 2019/9/30
     * @param instanceId: 流程实例ID
     * @return org.activiti.engine.task.Task
     */
    Task getTaskByInstanceId(String instanceId);

    /**
     * <p>验证流程是否停止</p>
     * @author chenyf
     * @date 2019/9/30
     * @param instanceId: 流程实例ID
     * @return java.lang.Boolean
     */
    Boolean validateProcess(String instanceId);


    /**
     * <p>获取当前用户所有的任务集合（包括group user assignee）</p>
     * @author chenyf
     * @date 2019/9/30
     * @param userId:
     * @return java.util.List<org.activiti.engine.task.Task>
     */
    List<Task> listAllTasksByUser(String userId);


    /**
     * <p>获取当前用户的待签收任务列表</p>
     * @author chenyf
     * @date 2019/9/30
     * @param assignee: 当前用户的ID
     * @return java.util.List<org.activiti.engine.task.Task>
     */
    List<Task> listTasksByAssignee(Integer assignee);

    /**
     * <p>完成任务</p>
     * @author chenyf
     * @date 2019/10/8
     * @param taskId: 任务ID
     * @param variables: 流程变量（map）
     * @param localScope: 变量作用域（true为本地变量）
     * @return void
     */
    void completeTask(String taskId, Map<String, Object> variables, Boolean localScope);

    /**
     * <p>认领任务</p>
     * @author chenyf
     * @date 2019/9/30
     * @param taskId: 任务ID
     * @param userId: 认领人ID
     * @return void
     */
    void claimTask(String taskId, Integer userId);

    /**
     * <p>获取当前任历史任务集合（已完成或者未完成）</p>
     * @author chenyf
     * @date 2019/9/30
     * @param assignee:
     * @return java.util.List<org.activiti.engine.history.HistoricTaskInstance>
     */
    List<HistoricTaskInstance> listHistoryTaskByAssignee(Integer assignee);

    /**
     * <p>转办任务</p>
     * @author chenyf
     * @date 2019/9/30
     * @param taskId: 任务ID
     * @param assignee: 转办人ID
     * @return void
     */
    void transferByAssignee(String taskId, Integer assignee);

    /**
     * <p>委托任务</p>
     * @author chenyf
     * @date 2019/9/30
     * @param taskId: 任务ID
     * @param assignee: 委托人
     * @return void
     */
    void delegateTaskByAssignee(String taskId, Integer assignee);

    /**
     * <p>终止任务</p>
     * @author chenyf
     * @date 2019/9/30
     * @param instanceId: 流程实例ID
     * @param deleteReason: 终止原因
     * @return void
     */
    void deleteProcess(String instanceId, String deleteReason);

    /**
     * <p>设置评论</p>
     * @author chenyf
     * @date 2019/9/30
     * @param assignee : 批注人Id
     * @param taskId : 任务Id
     * @param instanceId : 实例Id
     * @param procProgress
     * @param comment : 评论
     * @return Boolean
     */
    boolean addComment(Integer assignee, String taskId, String instanceId, Integer procProgress, String comment);

    /**
     * <p>设置评论</p>
     * @author chenyf
     * @date 2019/9/30
     * @param assignee : 批注人Id
     * @param taskId : 任务Id
     * @param instanceId : 实例Id
     * @param procProgress
     * @param comment : 评论
     * @return Boolean
     */
    boolean addComment(Integer assignee, String taskId, String instanceId, Integer procProgress, String comment, String action);

    /**
     * <p>获取历史流程实例详情</p>
     * @author chenyf
     * @date 2019/9/30
     * @param instanceId: 流程实例id
     * @return org.activiti.engine.history.HistoricProcessInstance
     */
    HistoricProcessInstance getHistoricProcessInstance(String instanceId);

    /**
     * 根据任务ID获取任务
     * @param taskId
     * @return
     */
    Task getTaskByTaskId(String taskId);

    /**
     * 根据实例ID获取所有的任务
     */
    List<Task> getTasksByInstanceId(String instanceId);



}
