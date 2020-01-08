package com.baizhi.lzz.controller;


import com.baizhi.lzz.entity.Guru;
import com.baizhi.lzz.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    GuruService guruService;

      @RequestMapping("/showAllPage")
    @ResponseBody
    public Map show(Integer page, Integer rows){
          System.out.println(page+"**************************"+rows);
          Map map = guruService.queryAll(page, rows);
          System.out.println(map+":--------------------");
        return map;
    }
    @RequestMapping("showAll")
    public List<Guru> showAllGuru(){
        List<Guru> list = guruService.selectAll();
        return list;
    }

    @RequestMapping("/edit")
    public Map edit(String oper, String[] id, Guru guru){
        HashMap map =new HashMap();
        if (oper.equals("add")){
            String guruId = UUID.randomUUID().toString();
            guru.setId(guruId);
            guruService.insertGuru(guru);
            map.put("guruId",guruId);

        }
        if (oper.equals("del")){
            guruService.delectGuru(id);

        }
        if (oper.equals("edit")){
            guruService.updateGuru(guru);
            map.put("guruId",guru.getId());
        }
        return map;
    }
    //17、展示上师表
    @RequestMapping("gurnAll")
    public Map gurnAll(String uid){
        List<Guru> list = guruService.selectAll();
        HashMap hashMap = new HashMap();
        hashMap.put("uid",uid);
        hashMap.put("status",200);
        hashMap.put("gurn",list);
        return hashMap;
    }


}
