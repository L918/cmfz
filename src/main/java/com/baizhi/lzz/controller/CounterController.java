package com.baizhi.lzz.controller;

import com.baizhi.lzz.entity.Counter;
import com.baizhi.lzz.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("counter")
public class CounterController {
    @Autowired
    CounterService counterService;
    //11、展示计数器
   @RequestMapping("showAll")
    public Map showAll(String uid,String id){
       HashMap hashMap = new HashMap();
       List list = counterService.showPage(uid, id);
       hashMap.put("status",200);
       hashMap.put("counters",list);
       return hashMap;
   }
    //12. 添加计数器
    @RequestMapping("insterCounter")
    public Map insterCounter(String uid,String title){
        Counter counter = new Counter();
        counter.setId(UUID.randomUUID().toString());
        counter.setTitle(title);
        counter.setUserId(uid);
        counter.setCreateDate(new Date());
       counterService.insterCounter(counter);
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("counters",counter);
       return hashMap;
    }
    //13. 删除计数器
    @RequestMapping("deleteCounter")
    public Map deleteCounter(String uid,String id){
        counterService.deleteCounter(id);
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        hashMap.put("uid",uid);
        return hashMap;
    }
    //14. 变更计数器
    @RequestMapping("updateCounter")
     public Map updateCounter(String uid,String id,Integer countion){
       Counter counter = new Counter();
       counter.setUserId(uid);
        System.out.println("id:="+id);
       counter.setCountion(countion);
       counter.setId(id);
       counterService.updateCounter(counter);
       HashMap hashMap = new HashMap();
       hashMap.put("status",200);
       hashMap.put("counters",counter);
       return hashMap;
    }

}
