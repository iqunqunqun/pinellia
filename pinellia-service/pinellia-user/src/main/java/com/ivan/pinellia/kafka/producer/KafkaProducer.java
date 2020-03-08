package com.ivan.pinellia.kafka.producer;

import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.secure.provider.CustomUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * <p>kafka生产者</p>
 *
 * @author user
 * @className KafkaProducer
 * @since 2020/3/7 11:13 上午
 */


@Component
public class KafkaProducer {

    private final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String topic, String data) {
        log.info("kafka sendMessage start");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("kafka sendMessage success topic = {}, data = {}",topic, data);
            }
        });
        log.info("kafka sendMessage end");
    }
}
