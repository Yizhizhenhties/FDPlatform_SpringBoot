package org.example.fdplatform.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.fdplatform.mapper.MyImageMapper;
import org.example.fdplatform.mapper.ValidateImgGroupMapper;
import org.example.fdplatform.model.domain.MyImage;
import org.example.fdplatform.model.domain.ValidateImgGroup;
import org.example.fdplatform.service.MyImageService;
import org.example.fdplatform.service.ValidateImgGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ValidateImgGroupServiceImpl extends ServiceImpl<ValidateImgGroupMapper, ValidateImgGroup> implements ValidateImgGroupService {

    @Autowired
    ValidateImgGroupMapper validateImgGroupMapper;

    @Autowired
    MyImageService myImageService;

    @Override
    public Map getValidateImgs() {
        Map<String, Object> re = new HashMap<>();
        List<Map<String, Object>> data = new ArrayList<>();
        // 随机选取一个ValidateImgGroup
        QueryWrapper<ValidateImgGroup> vig_queryWrapper = new QueryWrapper<>();
        vig_queryWrapper.orderBy(true, true, "rand()");
        vig_queryWrapper.last("limit 1");
        ValidateImgGroup validateImgGroup = validateImgGroupMapper.selectOne(vig_queryWrapper);
        // 将该ValidateImgGroup对应的图片取出
        String imguids = validateImgGroup.getImguids();
        String groupuuid = validateImgGroup.getGroupuuid();
        List<MyImage> list = myImageService.selectByUids(imguids, groupuuid);
        for(MyImage img:list){
            Map<String,Object> tempmap = new HashMap<>();
            tempmap.put("va_img",img.getBase64url());
            data.add(tempmap);
        }
        re.put("data", data);
        re.put("groupuuid", groupuuid);
        return re;
    }

    @Override
    public Map validate(String groupuuid, String answer) {
        Map<String, Object> re = new HashMap<>();
        QueryWrapper<ValidateImgGroup> vig_queryWrapper = new QueryWrapper<>();
        vig_queryWrapper.eq("groupuuid", groupuuid);
        ValidateImgGroup validateImgGroup = validateImgGroupMapper.selectOne(vig_queryWrapper);
        if (validateImgGroup != null){
            if(validateImgGroup.getAnswer().equals("-2")){
                re.put("state", 1);
            }
            else{
                if(validateImgGroup.getAnswer().equals(answer)) re.put("state", 1);
                else re.put("state", 0);
            }
        }
        else re.put("state", 0);
        return re;
    }

    @Override
    public int addValidateImgs(MultipartFile[] fileUpLoad, String answer) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        String validate_uuid = UUID.randomUUID().toString();
        StringBuilder imguids = new StringBuilder();
        if(fileUpLoad != null){
            for(MultipartFile multipartFile:fileUpLoad){
                byte[] multipartFileBytes = multipartFile.getBytes();
                String img_base64 = encoder.encodeToString(multipartFileBytes);
                MyImage myImage = new MyImage();
                String img_uuid = UUID.randomUUID().toString();
                myImage.setImguid(img_uuid);
                myImage.setBase64url(img_base64);
                myImageService.addMyImage(myImage);
                if(imguids.length() == 0) imguids.append(img_uuid);
                else imguids.append(";").append(img_uuid);
            }
        }
        ValidateImgGroup validateImgGroup = new ValidateImgGroup();
        validateImgGroup.setAnswer(answer);
        validateImgGroup.setImguids(imguids.toString());
        validateImgGroup.setGroupuuid(validate_uuid);
        if(validateImgGroupMapper.insert(validateImgGroup) > 0){
            return 1;
        }
        return 0;
    }
}
