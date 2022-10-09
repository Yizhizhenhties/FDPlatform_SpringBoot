package org.example.fdplatform.controller;
import org.example.fdplatform.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/processor")
public class ProcessorController {
    @Autowired
    ProcessorService processorService;

    @PostMapping("/process")
    public Map process(@RequestParam("file") MultipartFile[] fileUpload, @RequestParam("username") String username) throws Exception {
        return processorService.process(fileUpload, username);
    }
}
