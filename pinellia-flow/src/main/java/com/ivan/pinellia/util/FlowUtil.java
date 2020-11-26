package com.ivan.pinellia.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Lists;
import com.ivan.pinellia.constant.FlowConstant;
import com.ivan.pinellia.constant.PinelliaConstant;
import com.ivan.pinellia.model.ApprovalFlow;
import com.ivan.pinellia.model.ApprovalFlowVO;
import com.ivan.pinellia.model.FlowMessage;
import com.ivan.pinellia.model.FlowNodeVO;
import com.ivan.pinellia.service.IFlowCustomService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author chenyf
 * @className FlowUtil
 * @since 2020/5/27 9:40
 */

@Slf4j
public class FlowUtil {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    private static IFlowCustomService flowCustomService;

    private FlowUtil() {
        throw new IllegalStateException("Utility class");
    }

    static {
        flowCustomService = SpringUtil.getBean(IFlowCustomService.class);
    }

    /**
     * 动态生成流程（完整步骤）
     */
    public static void initProcess(List<ApprovalFlowVO> flowVOList) {
        log.info("---流程创建开始---");

        // 1：初始化动态流程
        FlowMessage FlowMessage = FlowUtil.initFlow(FlowConstant.UNDERLINE + UUID.randomUUID());

        // 2.创建节点
        // 2.1 整理节点
        List<FlowNodeVO> nodeList = organizeNodes(flowVOList);
        // 2.2 生成节点
        generateNode(FlowMessage, nodeList);

        // 3.连线
        FlowUtil.connection(FlowMessage);

        // 4.生成流程
        FlowUtil.generateFlow(FlowMessage);

        // 5.开启流程
        // 5.1 整理流程变量
        Map<String, Object> map = handleNodeVar(nodeList);
        // 5.2 启动流程
        ProcessInstance instance = FlowUtil.startProcess(FlowMessage, map);
        log.info("instance === {}", instance.getId());

        // 获取流程任务
        List<Task> tasks = flowCustomService.getTasksByInstanceId(instance.getProcessInstanceId());
        for (Task task : tasks) {
           log.info("task === {} ", task);
        }

        // 6.输出流程图
        FlowUtil.generateFlowBpmn(FlowMessage);
        log.info("---流程创建结束---");
    }

    /**
     * 生成节点
     * @param FlowMessage
     * @param nodeList
     */
    public static void generateNode(FlowMessage FlowMessage, List<FlowNodeVO> nodeList) {
        for (FlowNodeVO nodeVO : nodeList) {
            // 节点变量
            String nodeVar = RandomUtil.randomString(RandomUtil.BASE_CHAR, PinelliaConstant.SIX);
            nodeVO.setNodeVar(nodeVar);

            // 顺序节点
            if (PinelliaConstant.ZERO.equals(nodeVO.getNodeType())) {
                FlowUtil.createAssigneeFlowElement(FlowMessage, nodeVar);
            }

            // 会签节点
            if (PinelliaConstant.ONE.equals(nodeVO.getNodeType())) {
                FlowUtil.createMultiFlowElement(FlowMessage, FlowConstant.FLOW_COUNTERSIGN_CONDITION, nodeVar, RandomUtil.randomString(RandomUtil.BASE_CHAR, PinelliaConstant.SIX));
            }

            // 或签节点
            if (PinelliaConstant.TWO.equals(nodeVO.getNodeType())) {
                FlowUtil.createMultiFlowElement(FlowMessage, FlowConstant.FLOW_OR_SIGN_CONDITION, nodeVar, RandomUtil.randomString(RandomUtil.BASE_CHAR, PinelliaConstant.SIX));
            }

        }
    }

    /**
     * 处理流程变量
     * @param nodeList
     */
    public static Map<String, Object> handleNodeVar(List<FlowNodeVO> nodeList) {
        Map<String, Object> hashMap = new HashMap<>(64);
        for (FlowNodeVO flowNodeVO : nodeList) {
            List<ApprovalFlowVO> flowNodeList = flowNodeVO.getNodeList();

            // 顺序节点
            if (PinelliaConstant.ZERO.equals(flowNodeVO.getNodeType())) {
                ApprovalFlowVO flowVO = flowNodeList.get(PinelliaConstant.ZERO);
                hashMap.put(flowNodeVO.getNodeVar(), flowVO.getApproverStaffId());
                continue;
            }
            
            // 会签、或签节点
            List<Integer> approverList = flowNodeList.stream().map(ApprovalFlow::getApproverStaffId).collect(Collectors.toList());
            hashMap.put(flowNodeVO.getNodeVar(), approverList);
        }
        return hashMap;
    }

    /**
     * 整理节点
     * @param flowVOList
     */
    public static List<FlowNodeVO> organizeNodes(List<ApprovalFlowVO> flowVOList) {

        Set<Integer> nodeNumberSet = flowVOList.stream().map(ApprovalFlow::getNodeNumber).collect(Collectors.toSet());
        List<FlowNodeVO> nodeList = Lists.newArrayList();
        int index = 0;
        for (Integer nodeNumber : nodeNumberSet) {
            List<ApprovalFlowVO> nodeChildList = flowVOList.stream().filter(approvalFlowVO -> nodeNumber.equals(approvalFlowVO.getNodeNumber())).collect(Collectors.toList());
            ApprovalFlowVO flowVO = nodeChildList.get(PinelliaConstant.ZERO);
            FlowNodeVO nodeVO = FlowNodeVO.init(index, flowVO.getNodeType(), nodeChildList);
            nodeList.add(nodeVO);
            index++;
        }
        return nodeList;
    }

    /**
     * 第一步：建立模型
     * @param name: 流程名称
     * @return
     */
    public static FlowMessage initFlow(String name) {
        // 1. 建立模型
        FlowMessage FlowMessage = new FlowMessage();
        BpmnModel model = new BpmnModel();
        Process process=new Process();
        model.addProcess(process);
        // 设置流程基本信息
        process.setId(FlowConstant.UNDERLINE + RandomUtil.randomString(PinelliaConstant.FOUR));
        process.setName(FlowConstant.UNDERLINE + name);
        process.setDocumentation(RandomUtil.randomString(PinelliaConstant.FOUR));
        // 初始化开始节点
        process.addFlowElement(createStartEvent());

        FlowMessage.setBpmnModel(model);
        FlowMessage.setProcess(process);
        return FlowMessage;
    }

    /**
     * 生成的图形信息以及部署流程
     * @param FlowMessage: 流程元数据存储对象
     */
    public static void generateFlow(FlowMessage FlowMessage) {
        // 2. 生成的图形信息
        BpmnModel model = FlowMessage.getBpmnModel();
        new BpmnAutoLayout(model).execute();
        Process process = FlowMessage.getProcess();

        // 3. 部署流程
        Deployment deployment = processEngine
                .getRepositoryService()
                .createDeployment()
                .addBpmnModel(process.getId()+".bpmn", model)
                .name(process.getId()+"_deployment").deploy();
        FlowMessage.setDeployment(deployment);
    }

    /**
     * 生成流程图
     * @param FlowMessage
     */
    public static void generateFlowBpmn(FlowMessage FlowMessage) {
        try{
            // 6. 将流程图保存到本地文件
            InputStream processDiagram = processEngine.getRepositoryService().getProcessDiagram(FlowMessage.getProcessInstance().getProcessDefinitionId());
            FileUtils.copyInputStreamToFile(processDiagram, new File("E:\\logs\\"+FlowMessage.getProcess().getId()+".png"));

            // 7. 保存BPMN.xml到本地文件
            InputStream processBpmn = processEngine.getRepositoryService().getResourceAsStream(FlowMessage.getDeployment().getId(), FlowMessage.getProcess().getId()+".bpmn");
            FileUtils.copyInputStreamToFile(processBpmn,new File("E:\\logs\\"+FlowMessage.getProcess().getId()+".bpmn"));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 开启流程
     */
    public static ProcessInstance startProcess(FlowMessage FlowMessage, Map<String, Object> hashMap) {
        ProcessInstance processInstance = flowCustomService.startProcess(FlowMessage.getProcess().getId(), hashMap);
        FlowMessage.setProcessInstance(processInstance);
        return processInstance;
    }

    /**
     * 完成流程
     */
    public static void complete(String taskId, Map<String, Object> hashMap) {
        flowCustomService.completeTask(taskId, hashMap, false);
    }

    /**
     * 转交流程
     */
    public static void forward(String taskId, Integer assignee) {
        flowCustomService.transferByAssignee(taskId, assignee);
    }

    /**
     * 拒绝流程
     */
    public static void refuse(String instanceId, String deleteReason) {
        flowCustomService.deleteProcess(instanceId, deleteReason);
    }



    /**
     * 创建任务指定人任务节点
     * @param userPkno
     */
    public static void createAssigneeFlowElement(FlowMessage FlowMessage, String userPkno) {
        String id = FlowConstant.UNDERLINE + RandomUtil.randomString(PinelliaConstant.FOUR);
        String name = FlowConstant.UNDERLINE + RandomUtil.randomString(PinelliaConstant.FOUR);
        createAssigneeFlowElement(FlowMessage, id, name, userPkno);
    }


    /**
     * 创建任务指定人任务节点
     * @param userPkno
     */
    public static void createAssigneeFlowElement(FlowMessage FlowMessage, String id, String name, String userPkno) {
        UserTask task = createAssigneeTask(id, name, userPkno);
        FlowMessage.getProcess().addFlowElement(task);
    }

    /**
     * 创建多实例任务节点
     */
    public static void createMultiFlowElement(FlowMessage FlowMessage, String completionCondition, String inputDataItem, String elementVariable) {
        String id = FlowConstant.UNDERLINE + RandomUtil.randomString(PinelliaConstant.FOUR);
        String name = FlowConstant.UNDERLINE + RandomUtil.randomString(PinelliaConstant.FOUR);
        createMultiFlowElement(FlowMessage, id, name, completionCondition, inputDataItem, elementVariable);
    }

    /**
     * 创建多实例任务节点
     */
    public static void createMultiFlowElement(FlowMessage FlowMessage, String id, String name, String completionCondition, String inputDataItem, String elementVariable) {
        UserTask multiTask = createMultiTask(id, name, completionCondition, inputDataItem, elementVariable);
        FlowMessage.getProcess().addFlowElement(multiTask);
    }

    /**
     * 连线
     */
    public static void connection(FlowMessage FlowMessage) {
        Process process = FlowMessage.getProcess();
        // 初始化结束节点
        process.addFlowElement(createEndEvent());
        List<FlowElement> elements = Lists.newArrayList(process.getFlowElements());
        for (int i = 1; i < elements.size(); i++) {
            FlowElement beforeFlowElement = elements.get(i - 1);
            FlowElement afterFlowElement = elements.get(i);
            process.addFlowElement(createSequenceFlow(beforeFlowElement.getId(), afterFlowElement.getId(), FlowConstant.UNDERLINE + UUID.randomUUID().toString(), ""));
        }
    }

    /**
     * 任务节点-组
     * @param id：任务的ID
     * @param name：任务的名称
     * @param candidateGroup：任务的候选人组
     * @return
     */
    public static UserTask createGroupTask(String id, String name, String candidateGroup) {
        List<String> candidateGroups=new ArrayList<String>();
        candidateGroups.add(candidateGroup);
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setCandidateGroups(candidateGroups);
        return userTask;
    }

    /**
     * 任务节点-用户
     * @param id：任务节点ID
     * @param name：任务节点名称
     * @param userPkno：任务处理人变量字符串（如：userId）
     * @return
     */
    public static UserTask createUserTask(String id, String name, String userPkno) {
        List<String> candidateUsers=new ArrayList<String>();
        candidateUsers.add("${" + userPkno + "}");
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setCandidateUsers(candidateUsers);
        return userTask;
    }

    /**
     * 任务节点-锁定者
     * @param id：任务节点ID
     * @param name：任务节点名称
     * @param userPkno：任务处理人变量（如：userId）
     * @return
     */
    public static UserTask createAssigneeTask(String id, String name, String userPkno) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee("${" + userPkno + "}");
        return userTask;
    }

    /**
     * 任务节点-多实例
     * @param id ：多实例节点的ID
     * @param name：多实例节点的名称
     * @return
     */
    public static UserTask createMultiTask(String id, String name, String completionCondition, String inputDataItem, String elementVariable) {
        UserTask userTask = new UserTask();
        MultiInstanceLoopCharacteristics characteristics = new MultiInstanceLoopCharacteristics();
        characteristics.setSequential(false);
        characteristics.setCompletionCondition("${" + completionCondition + "}");
        characteristics.setInputDataItem("${"+ inputDataItem +"}");
        characteristics.setElementVariable(elementVariable);
        userTask.setLoopCharacteristics(characteristics);
        userTask.setAssignee("${" + elementVariable + "}");
        userTask.setName(name);
        userTask.setId(id);
        return userTask;
    }

    /**
     * 任务节点-多实例
     * @param id ：多实例节点的ID
     * @param name：多实例节点的名称
     * @return
     */
    public static UserTask createMultiTask(String id, String name, String inputDataItem, String elementVariable) {
        return createMultiTask(id, name, FlowConstant.FLOW_COUNTERSIGN_CONDITION, inputDataItem, elementVariable);
    }

    /**
     * 连线
     * @param from:连接开始节点ID
     * @param to：连接结束节点ID
     * @param name：连线名称
     * @param conditionExpression：连线包含的表达式
     * @return
     */
    public static SequenceFlow createSequenceFlow(String from, String to, String name, String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);
        if(StrUtil.isNotBlank(conditionExpression)){
            flow.setConditionExpression(conditionExpression);
        }
        return flow;
    }

    /**
     * 排他网关
     * @param id:排他网关ID
     * @param name：排他网关名称
     * @return
     */
    public static ExclusiveGateway createExclusiveGateway(String id, String name) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);
        return exclusiveGateway;
    }

    /**
     * 并行网关
     * @param id:并行网关ID
     * @param name：并行网关名称
     * @return
     */
    public static ParallelGateway createParallelGateway(String id, String name){
        ParallelGateway gateway = new ParallelGateway();
        gateway.setId(id);
        gateway.setName(name);
        return gateway;
    }

    /**
     * 开始节点
     * @return：开始节点
     */
    public static StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(FlowConstant.FLOW_START_EVENT);
        return startEvent;
    }

    /**
     * 结束节点
     * @return: 结束节点
     */
    public static EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(FlowConstant.FLOW_END_EVENT);
        return endEvent;
    }
}
