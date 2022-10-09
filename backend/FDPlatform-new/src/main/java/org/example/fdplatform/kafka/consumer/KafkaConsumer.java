package org.example.fdplatform.kafka.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.fdplatform.mapper.HistoryMapper;
import org.example.fdplatform.mapper.MyImageMapper;
import org.example.fdplatform.model.domain.History;
import org.example.fdplatform.model.domain.MyImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class KafkaConsumer {
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    MyImageMapper myImageMapper;

    @KafkaListener(topics = {"history"})
    public void handMessage(ConsumerRecord<String, String> record){
        String message = record.value();
        Map<String, Object> result = JSON.parseObject(message);
        String his_uuid = UUID.randomUUID().toString();
        Date create_time = new Date();
        List<Map<String, Object>> resultImgMapList = (List<Map<String, Object>>) result.get("data");
        History history = new History();
        int length = 0;
        int pro_length = 0;
        StringBuilder imguids = new StringBuilder();
        for(Map<String, Object> resultImgMap: resultImgMapList){
            if((boolean)resultImgMap.get("is_fabric")){
                length++;
                if(((String)resultImgMap.get("is_normal")).equals("True")){
                    pro_length++;
                }
                String img_uuid = UUID.randomUUID().toString();
                MyImage myImage = new MyImage();
                myImage.setImguid(img_uuid);
                myImage.setBase64url((String)resultImgMap.get("heat"));
                myImageMapper.insert(myImage);
                if(imguids.length() == 0) imguids.append(img_uuid);
                else imguids.append(";").append(img_uuid);
            }
        }
        history.setCreatetime(create_time);
        history.setUpdatetime(create_time);
        history.setHisUuid(his_uuid);
        history.setLength(length);
        history.setProLength(pro_length);
        history.setUsername((String) result.get("username"));
        history.setImguids(imguids.toString());
        historyMapper.insert(history);
    }
}
