package com.baizhi.lzz.entity.BannerWord;

import com.alibaba.excel.EasyExcel;
import com.baizhi.lzz.dao.BannerDao;
import com.baizhi.lzz.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("bannerapi")
public class BannerApi {
    @Autowired
    BannerDao bannerDao;
    @RequestMapping("testBanner")
    public void testBanner(){
        String fileName = "D:\\学习文件\\新建文件夹\\178\\后期项目\\day7-poiEasyExcel\\poi练习\\"+new Date().getTime()+".xlsx";
        List<Banner> banners = bannerDao.selectAll();
        System.out.println(banners);
//        EasyExcel.write(fileName, Banner.class).sheet("轮播图信息") .doWrite(banners);
        EasyExcel.write(fileName, Banner.class).sheet("轮播图信息") .doWrite(banners);
    }

    //导入日志信息
    public void addExportUser(MultipartFile log){
        String url = "E:\\idea3\\cmfz\\src\\main\\webapp\\upload\\ExcelLog\\"+log.getOriginalFilename();
        EasyExcel.read(url, Banner.class,new BannerListener()).sheet("信息导入日志").doRead();
    }
}
