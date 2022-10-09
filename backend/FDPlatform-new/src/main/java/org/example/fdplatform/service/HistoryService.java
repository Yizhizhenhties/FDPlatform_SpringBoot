package org.example.fdplatform.service;
import java.util.Map;

public interface HistoryService {
    Map getHistoryImgs(String imguids, String hisUuid);
    Map getHistoryList(String username);
}
