package com.ivan.pinellia.kafka.consumer;

import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.tool.constant.KafkaConstant;
import com.ivan.pinellia.tool.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>kafka消费者</p>
 *
 * @author user
 * @className KafkaConsumer
 * @since 2020/3/7 11:13 上午
 */


@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * 监听seckill主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {KafkaConstant.USER_TOPIC}, groupId = KafkaConstant.USER_GROUP)
    public void receiveMessageGroup(String message){
        //收到通道的消息之后执行秒杀操作
        logger.info("========接收到user one的信息啦==========");
        logger.info("----------user group one------------{}", message);
        System.out.println("message one = " + message);
        User parse = JsonUtil.parse(message, User.class);
        System.out.println("parse one = " + parse);
        logger.info("========处理user one结束==========");
    }


    /**
     * 监听seckill主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {KafkaConstant.DEPT_TOPIC}, groupId = KafkaConstant.DEPT_GROUP)
    public void receiveDeptMessage(String message){
        //收到通道的消息之后执行秒杀操作
        logger.info("========接收到dept的信息啦==========");
        logger.info("----------dept group------------{}", message);
        System.out.println("message = " + message);
        List<Dept> deptList = JsonUtil.parseArray(message, Dept.class);
        System.out.println("parse = " + deptList);
        logger.info("========处理dept结束==========");
    }


}

