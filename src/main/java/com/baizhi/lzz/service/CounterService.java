package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Counter;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface CounterService {
    //11、展示计数器
    List<Counter> showPage(String uid, String id);
    //12、添加计数器
    void insterCounter(Counter counter);
    //13、删除计数器
    void deleteCounter(String id);
    //14、变更计数器
    void updateCounter(Counter counter);

}
