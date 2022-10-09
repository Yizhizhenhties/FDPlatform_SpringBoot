package org.example.fdplatform.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.fdplatform.mapper.HistoryMapper;
import org.example.fdplatform.model.domain.History;
import org.example.fdplatform.model.domain.MyImage;
import org.example.fdplatform.service.HistoryService;
import org.example.fdplatform.service.MyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoryImpl extends ServiceImpl<HistoryMapper, History>  implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    MyImageService myImageService;

    @Override
    public Map getHistoryImgs(String imguids, String hisUuid) {
        List<Map<String, Object>> data = new ArrayList<>();
        List<MyImage> list = myImageService.selectByUids(imguids, hisUuid);
        for(MyImage img:list){
            Map<String,Object> tempmap = new HashMap<>();
            tempmap.put("img",img.getBase64url());
            data.add(tempmap);
        }
        Map<String, Object> re = new HashMap<>();
        re.put("data",data);
        return re;
    }

    @Override
    public Map getHistoryList(String username) {
        List<Map<String, Object>> data = new ArrayList<>();
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.orderByDesc("createtime");
        List<History> list = historyMapper.selectList(queryWrapper);
        for(History his:list){
            Map<String ,Object> map = new HashMap<>();
            map.put("uuid",his.getHisUuid());
            map.put("username",his.getUsername());
            map.put("length",his.getLength());
            map.put("pro_length",his.getProLength());
            map.put("imguids",his.getImguids());
            map.put("createtime",his.getCreatetime());
            map.put("updatetime",his.getUpdatetime());
            data.add(map);
        }
        Map<String, Object> re = new HashMap<>();
        re.put("data",data);
        return re;
    }
}
