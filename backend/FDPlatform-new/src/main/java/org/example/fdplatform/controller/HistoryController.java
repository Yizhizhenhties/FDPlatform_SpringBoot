package org.example.fdplatform.controller;

import org.example.fdplatform.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @PostMapping("/historylist")
    public Map getHistoryList(@RequestParam("username") String username){
        return historyService.getHistoryList(username);
    }

    @PostMapping("/gethistoryimgs")
    public Map getHistoryImgs(@RequestParam("imguids") String imguids, @RequestParam("hisuuid") String hisUuid){
        return historyService.getHistoryImgs(imguids, hisUuid);
    }
}
