package com.baizhi.lzz.controller.ApiInterface;


import com.baizhi.lzz.entity.Banner;
import com.baizhi.lzz.entity.Guru;
import com.baizhi.lzz.entity.Ug;
import com.baizhi.lzz.entity.User;
import com.baizhi.lzz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("onePage")
// 需要返回json格式的数据
@RestController
public class OnePageController {
    @Autowired
    BannerService bannerService;
    @Autowired
    AlbumService albumService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    GuruService guruService;
    @Autowired
    Ugservice ugservice;


    @RequestMapping("onePage")
    // 5.type : all|wen|si
    public Map onePage(String uid,String type,String sub_type){
        HashMap hashMap = new HashMap();
        try {
            if (type.equals("all")){
                List<Banner> banners = bannerService.queryBannersByTime();
                Map albums =albumService.queryAll(1,5);
                Map articles = articleService.selectAll(1, 5);
                hashMap.put("status",200);
                hashMap.put("head",banners);
                hashMap.put("albums",albums);
                hashMap.put("articles",articles);
            }else if (type.equals("wen")){
                Map albums =albumService.queryAll(1,5);
                hashMap.put("status",200);
                hashMap.put("albums",albums);
            }else {
                if (sub_type.equals("ssyj")){
                    //根绝上师Id查所属文章
                    Map articles = articleService.selectIdAll(1, 5, uid);

                    hashMap.put("status",200);

                    hashMap.put("articles",articles);
                }else {
                    Map articles = articleService.selectAll(1, 5);
                    hashMap.put("status",200);
                    hashMap.put("articles",articles);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("status","-200");
            hashMap.put("message","error");
        }
        return hashMap;
    }
    //6、文章详情        uid 用户id，id 文章id
    @RequestMapping("articleTitle")
    public Map articleTitle(String uid,String id){
        Map map = new HashMap();
        map.put("article",articleService.articleTitle(id));
        map.put("uid",uid);
        return map;
    }

    //7、专辑详情
    @RequestMapping("albumTitle")
    public Map albumTitle(String uid,String id){
        Map map = new HashMap();
        map.put("album",albumService.albumTitle(id));
        map.put("uid",uid);
        return map;
    }





    //18、关注上师
    @RequestMapping("userGurn")
    public Map userGurn(String uid,String id){
        HashMap hashMap = new HashMap();
        Ug ug = new Ug();
        ug.setUserId(uid);
        ug.setGuruId(id);
        ug.setId(UUID.randomUUID().toString());
        ugservice.addUg(ug);
        System.out.println(ug);
        hashMap.put("status",200);
        hashMap.put("mession","关注成功");
        hashMap.put("ug",ug);
        return hashMap;
    }







}
