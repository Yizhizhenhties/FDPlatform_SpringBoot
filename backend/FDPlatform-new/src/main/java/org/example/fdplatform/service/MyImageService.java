package org.example.fdplatform.service;

import org.example.fdplatform.model.domain.MyImage;

import java.util.List;

public interface MyImageService {
    int addMyImage(MyImage myImage);
    MyImage selectByUid(String uid);
    List<MyImage> selectByUids(String uids, String itemUuid);
}
