package org.example.fdplatform.controller;

import org.example.fdplatform.service.ValidateImgGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/validateimg")
public class ValidateImgGroupController {

    @Autowired
    ValidateImgGroupService validateImgGroupService;

    @PostMapping("/getvalidateimgs")
    public Map getValidateImgs(){
        return validateImgGroupService.getValidateImgs();
    }

    @PostMapping("/validate")
    public Map validate(@RequestParam("groupuuid") String groupuuid, @RequestParam("answer") String answer){ return validateImgGroupService.validate(groupuuid, answer); }

    @PostMapping("/addvalidateimgs")
    public int addValidateImgs(@RequestParam("file")MultipartFile[] files, @RequestParam("answer") String answer) throws IOException {
        return validateImgGroupService.addValidateImgs(files, answer);
    }
}
