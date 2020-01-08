package com.baizhi.lzz.controller;

import com.baizhi.lzz.entity.Album;
import com.baizhi.lzz.service.AlbumService;
import com.baizhi.lzz.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("/showAll")
    @ResponseBody
    public Map show(Integer page,Integer rows){
       return albumService.queryAll(page,rows);
    }

    @RequestMapping("/edit")
    public Map edit(Album album, String oper, String[] id){
        HashMap hashMap =new HashMap();
            if(oper.equals("add")){
                String albumId = UUID.randomUUID().toString();
                album.setId(albumId);
                albumService.insertOne(album);
                hashMap.put("albumId",albumId);
            }
            if(oper.equals("del")){
                albumService.deleteOne(id);
            }
            if(oper.equals("edit")){
                albumService.updateOne(album);
                hashMap.put("albumId",album.getId());
            }
        return hashMap;
    }
    @RequestMapping("/upload")
    // MultipartFile url(上传的文件),String bannerId(轮播图Id 更新使用)
    public Map upload(MultipartFile cover, String albumId, HttpSession session, HttpServletRequest request) {
        /*
            1. 相对路径 : /upload/img/XXX.jpg 优:方便处理 缺:耦合度较高,在网络环境下难以定位
            2. 网络路径 : http://localhost:9999/cmfz/upload/img/XXX.jpg 缺:处理麻烦(需要程序员手动处理) 优:定位,精准度较高
         */
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/album");
        //判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()){
         file.mkdirs();
        }
        String http = HttpUtil.getHttp(cover,request,"/upload/album/");
        //文件上传 工具类
        //更新数据库信息
        Album album = new Album();
        album.setId(albumId);
        album.setCover(http);
        albumService.updateOne(album);
        return hashMap;
    }

}
