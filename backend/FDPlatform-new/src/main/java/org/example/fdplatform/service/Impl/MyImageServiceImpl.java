package org.example.fdplatform.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.fdplatform.mapper.MyImageMapper;
import org.example.fdplatform.model.domain.MyImage;
import org.example.fdplatform.service.MyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MyImageServiceImpl extends ServiceImpl<MyImageMapper, MyImage> implements MyImageService {

    @Autowired
    MyImageMapper myImageMapper;

    @Override
    public int addMyImage(MyImage myImage) {
       if(myImageMapper.insert(myImage) > 0){
           return 1;
       }
       else{
           return 0;
       }
    }

    @Override
    public MyImage selectByUid(String uid) {
        QueryWrapper<MyImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("imguid", uid);
        return myImageMapper.selectOne(queryWrapper);
    }



    @Cacheable(cacheNames = "imglist", key = "#itemUuid")
    @Override
    public List<MyImage> selectByUids(String uids, String itemUuid) {
        String[] temp = uids.split(";");
        List<String> imguids_list = new ArrayList<>();
        Collections.addAll(imguids_list, temp);
        QueryWrapper<MyImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("imguid", imguids_list);
        return myImageMapper.selectList(queryWrapper);
    }
}
