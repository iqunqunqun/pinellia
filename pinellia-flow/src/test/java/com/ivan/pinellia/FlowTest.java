package com.ivan.pinellia;

import com.google.common.collect.Lists;
import com.ivan.pinellia.model.ApprovalFlowVO;
import com.ivan.pinellia.util.FlowUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * <p></p>
 *
 * @author ivan
 * @className FlowTest
 * @since 2020/11/26 21:22
 */
@SpringBootTest(classes = {FlowApplication.class})
@RunWith(SpringRunner.class)
public class FlowTest {

    @Test
    public void flow() {

        ApprovalFlowVO one = new ApprovalFlowVO();
        // ID
        one.setApproverStaffId(1);

        // 审批人类型（0-指定人，1-1级上级，2-2级上级）
        one.setApproverType(0);

        // 审批节点序号
        one.setNodeNumber(0);

        // 节点类型（0.顺序,1.会签节点,2.或签节点）
        one.setNodeType(0);

        ApprovalFlowVO two = new ApprovalFlowVO();
        // ID
        two.setApproverStaffId(2);

        // 审批人类型（0-指定人，1-1级上级，2-2级上级）
        two.setApproverType(0);

        // 审批节点序号
        two.setNodeNumber(1);

        // 节点类型（0.顺序,1.会签节点,2.或签节点）
        two.setNodeType(1);

        ApprovalFlowVO three = new ApprovalFlowVO();
        // ID
        three.setApproverStaffId(3);

        // 审批人类型（0-指定人，1-1级上级，2-2级上级）
        three.setApproverType(0);

        // 审批节点序号
        three.setNodeNumber(1);

        // 节点类型（0.顺序,1.会签节点,2.或签节点）
        three.setNodeType(1);

        ApprovalFlowVO four = new ApprovalFlowVO();
        // ID
        four.setApproverStaffId(4);

        // 审批人类型（0-指定人，1-1级上级，2-2级上级）
        four.setApproverType(0);

        // 审批节点序号
        four.setNodeNumber(2);

        // 节点类型（0.顺序,1.会签节点,2.或签节点）
        four.setNodeType(2);

        ApprovalFlowVO five = new ApprovalFlowVO();
        // ID
        five.setApproverStaffId(5);

        // 审批人类型（0-指定人，1-1级上级，2-2级上级）
        five.setApproverType(0);

        // 审批节点序号
        five.setNodeNumber(2);

        // 节点类型（0.顺序,1.会签节点,2.或签节点）
        five.setNodeType(2);

        ApprovalFlowVO six = new ApprovalFlowVO();
        // ID
        six.setApproverStaffId(6);

        // 审批人类型（0-指定人，1-1级上级，2-2级上级）
        six.setApproverType(0);

        // 审批节点序号
        six.setNodeNumber(3);

        // 节点类型（0.顺序,1.会签节点,2.或签节点）
        six.setNodeType(0);


        ArrayList<ApprovalFlowVO> list = Lists.newArrayList(one);

        FlowUtil.initProcess(list);

    }



}
