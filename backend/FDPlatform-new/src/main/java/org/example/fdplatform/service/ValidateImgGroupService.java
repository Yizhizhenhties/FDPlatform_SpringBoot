package org.example.fdplatform.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ValidateImgGroupService {
    Map getValidateImgs();
    Map validate(String groupuuid, String answer);

    int addValidateImgs(MultipartFile[] fileUpLoad, String answer) throws IOException;
}
