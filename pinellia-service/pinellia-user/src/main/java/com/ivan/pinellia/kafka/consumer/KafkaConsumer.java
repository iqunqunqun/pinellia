package com.ivan.pinellia.kafka.consumer;

import com.ivan.pinellia.entity.Dept;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.tool.constant.KafkaConstant;
import com.ivan.pinellia.tool.json.JsonUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

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

    private boolean checkRecord(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        if (value.isPresent()) {
            logger.info("topic - {}", record.topic());
            logger.info("partition - {}", record.partition());
            logger.info("offset - {}", record.offset());
            return true;
        }
        return false;
    }

    /**
     * 监听seckill主题,有消息就读取
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.USER_TOPIC, KafkaConstant.DEPT_TOPIC}, groupId = KafkaConstant.USER_GROUP)
    public void receiveMessageGroup(ConsumerRecord<?, ?> record){

        boolean b = checkRecord(record);
        if (b) {
            //收到通道的消息之后执行秒杀操作
            logger.info("========接收到user one的信息啦==========");
            logger.info("----------user group one------------{}", record.value().toString());
            User parse = JsonUtil.parse(record.value().toString(), User.class);
            System.out.println("parse one = " + parse);
            logger.info("User sex: {}", Objects.requireNonNull(parse).getSex());
            logger.info("========处理user one结束==========");
        }

    }


    /**
     * 监听seckill主题,有消息就读取
     * @param record
     */
    @KafkaListener(topics = {KafkaConstant.DEPT_TOPIC}, groupId = KafkaConstant.DEPT_GROUP)
    public void receiveDeptMessage(ConsumerRecord<?, ?> record){
        boolean b = checkRecord(record);
        if (b) {
            //收到通道的消息之后执行秒杀操作
            logger.info("========接收到dept的信息啦==========");
            logger.info("----------dept group------------{}", record.value().toString());
            List<Dept> deptList = JsonUtil.parseArray(record.value().toString(), Dept.class);
            System.out.println("parse = " + deptList);
            logger.info("========处理dept结束==========");
        }
    }


}

