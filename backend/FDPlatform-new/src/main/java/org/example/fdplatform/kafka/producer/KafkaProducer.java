package org.example.fdplatform.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发送消息
     */
    public void sendMessage(String message) {
        try{
            //生产消息
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("history", "history", message);
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                }
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("sendMessage error");
                }
            });
        }catch (Exception e){
            System.out.println("sendMessage exception");
        }

    }
}