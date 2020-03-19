package com.ivan.pinellia.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

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
    private KafkaTemplate kafkaTemplate;


    @SuppressWarnings("unchecked")
    public <T> void sendMessage(String topic, T data) {
        log.info("kafka sendMessage start");
        log.info("kafka topic partition - {}", kafkaTemplate.partitionsFor(topic));
        ListenableFuture<SendResult<String, T>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                log.info("kafka sendMessage success topic = {}, data = {}, partition = {}",topic, data, result.getProducerRecord().partition());
            }
        });
        log.info("kafka sendMessage end");
    }

}
