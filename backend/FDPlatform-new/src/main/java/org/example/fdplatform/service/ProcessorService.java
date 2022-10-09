package org.example.fdplatform.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ProcessorService {
    Map process(MultipartFile[] fileUpload, String username) throws Exception;
}
