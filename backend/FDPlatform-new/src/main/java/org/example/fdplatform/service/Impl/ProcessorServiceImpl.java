package org.example.fdplatform.service.Impl;

import com.alibaba.fastjson.JSON;
import org.example.fdplatform.kafka.producer.KafkaProducer;
import org.example.fdplatform.service.HistoryService;
import org.example.fdplatform.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProcessorServiceImpl implements ProcessorService {

    @Autowired
    HistoryService historyService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private KafkaProducer kafkaProducer;

    public static final long THREAD_STACK_SIZE = 256;
    private static final String IMAGE_QUEUE = "imageQueue";
    private static final String CONSULT_OUT = "consultOut";
    private static final String IMAGE_KEY = "imageKey";
    private static final String IMAGE_URLS = "imageUrls";

    private static final Set<Long> TIME_INTERVALS = new HashSet<Long>() {
        {
            this.add(200l);
            this.add(400l);
            this.add(800l);
        }
    };


    @Override
    public Map process(MultipartFile[] fileUpload, String username) throws Exception {
        Map<String, Object> result = null;
        if(fileUpload != null) {
            //step 1. Is the queue overrun
            if (redisTemplate.opsForList().size(IMAGE_QUEUE) > THREAD_STACK_SIZE) {
                throw new Exception("System busy, Please try again later");
            }
            String[] fileUpload_base64 = new String[fileUpload.length];
            for(int i = 0; i < fileUpload.length; i++){
                fileUpload_base64[i] = Base64.getEncoder().encodeToString(fileUpload[i].getBytes());
            }
            //step 2. Put imageUrl in queue
            Map<String, Object> imageInfo = new HashMap<>();
            String imageKey = UUID.randomUUID().toString();
            imageInfo.put(IMAGE_KEY, imageKey);
            imageInfo.put(IMAGE_URLS, fileUpload_base64);
            redisTemplate.opsForList().leftPush(IMAGE_QUEUE, JSON.toJSONString(imageInfo));
            //step 3. Get the result
            for (Long interval : TIME_INTERVALS) {
                try {
                    Thread.currentThread().sleep(interval.longValue());
                    Object consultResult = redisTemplate.opsForHash().get(imageKey, CONSULT_OUT);
                    if (consultResult != null) {
                        //delete result in cache
                        redisTemplate.delete(imageKey);
                        result = JSON.parseObject((String) consultResult);
                        if(username != null){
                            // 写入数据库
                            result.put("username", username);
                            kafkaProducer.sendMessage(JSON.toJSONString(result));
                        }
                        break;
                    }
                } catch (InterruptedException e) {
                }
            }
        }
        return result;
    }
}
