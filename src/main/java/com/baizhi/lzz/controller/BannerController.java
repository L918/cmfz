package com.baizhi.lzz.controller;

import com.baizhi.lzz.dao.BannerDao;
import com.baizhi.lzz.entity.Banner;
import com.baizhi.lzz.entity.BannerPageDto;
import com.baizhi.lzz.service.BannerService;
import com.baizhi.lzz.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    @RequestMapping("/showAll")
    @ResponseBody
    public BannerPageDto show(int page, int rows){
        return bannerService.queryByPage(page, rows);
    }

    @RequestMapping("/edit")
    public Map edit(Banner banner, String oper,String[] id){
        HashMap hashMap = new HashMap();
        if (oper.equals("add")) {
            String bannerId = UUID.randomUUID().toString();
            banner.setId(bannerId);
            bannerService.addBanner(banner);
            hashMap.put("bannerId", bannerId);
        }
        if (oper.equals("del")) {
            bannerService.deleteBanner(id);
        }
        if (oper.equals("edit")) {
            bannerService.updataBanner(banner);
            hashMap.put("bannerId", banner.getId());
        }
        return hashMap;
    }

    @RequestMapping("/upload")
    // MultipartFile url(上传的文件),String bannerId(轮播图Id 更新使用)
    public Map upload(MultipartFile url, String bannerId, HttpSession session, HttpServletRequest request) {
        /*
            1. 相对路径 : /upload/img/XXX.jpg 优:方便处理 缺:耦合度较高,在网络环境下难以定位
            2. 网络路径 : http://localhost:9999/cmfz/upload/img/XXX.jpg 缺:处理麻烦(需要程序员手动处理) 优:定位,精准度较高
         */
        HashMap hashMap = new HashMap();
        // 获取真实路径
        String realPath = session.getServletContext().getRealPath("/upload/img/");
        // 判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String http = HttpUtil.getHttp(url, request, "/upload/img/");
        // 文件上传 工具类完成
        // 更新数据库信息
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setUrl(http);
        bannerService.updataBanner(banner);
        hashMap.put("status", 200);
        return hashMap;
    }
}
