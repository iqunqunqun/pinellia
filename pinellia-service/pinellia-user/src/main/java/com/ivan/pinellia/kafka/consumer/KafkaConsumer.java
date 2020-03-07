package com.ivan.pinellia.kafka.consumer;

import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.tool.constant.KafkaConstant;
import com.ivan.pinellia.tool.json.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>kafka消费者</p>
 *
 * @author user
 * @className KafkaConsumer
 * @since 2020/3/7 11:13 上午
 */

@Component
public class KafkaConsumer {

    /**
     * 监听seckill主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {KafkaConstant.USER_TOPIC}, groupId = KafkaConstant.USER_GROUP)
    public void receiveMessage(String message){
        //收到通道的消息之后执行秒杀操作
        System.out.println("message = " + message);
        User parse = JsonUtil.parse(message, User.class);
        System.out.println("parse = " + parse);
    }


}

