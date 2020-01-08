package com.baizhi.lzz;

import com.alibaba.excel.EasyExcel;
import com.baizhi.lzz.dao.BannerDao;
import com.baizhi.lzz.entity.Banner;
import com.baizhi.lzz.entity.BannerWord.BannerListener;
import com.baizhi.lzz.entity.BannerWord.ImageData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BannerPoi {
    @Autowired
    BannerDao bannerDao;

    //导出
    @Test
    public void testBanner(){
        String fileName = "D:\\学习文件\\新建文件夹\\178\\后期项目\\day7-poiEasyExcel\\poi练习\\"+new Date().getTime()+".xlsx";
        List<Banner> banners = bannerDao.selectAll();
        System.out.println(banners);
//        EasyExcel.write(fileName, Banner.class).sheet("轮播图信息") .doWrite(banners);
        EasyExcel.write(fileName, Banner.class).sheet("轮播图信息") .doWrite(banners);
    }
    //导入
    @Test
    public void testdaoru(){
//        String fileName =  TestFileUtil.getPath() + "banner" + File.separator + "1578032743079.xlsx";
        String fileName = "D:\\学习文件\\新建文件夹\\178\\后期项目\\day7-poiEasyExcel\\poi练习\\1578032743079.xlsx";
        EasyExcel.read(fileName, Banner.class, new BannerListener()).sheet().doRead();
    }


    @Test
    //MultipartFile log
    public void addExportUser(){
        String url = "D:\\学习文件\\新建文件夹\\178\\后期项目\\day7-poiEasyExcel\\poi练习\\";
        EasyExcel.read(url, Banner.class,new BannerListener()).sheet("日志信息").doRead();
    }


    @Test
    public void test04() throws MalformedURLException {
        String fileName = "D:\\学习文件\\新建文件夹\\178\\后期项目\\day7-poiEasyExcel\\poi练习\\"+new Date().getTime()+".xlsx";
        // write() 参数1:文件路径 参数2:实体类.class sheet()指定写入工作簿的名称 doWrite(List数据) 写入操作
        // 如需下载使用 参数1:outputSteam 参数2:实体类.class
        List<Banner> banners = bannerDao.selectAll();
        ImageData imageData = new ImageData("img/1577438782612_bulb_off1.jpg");
        EasyExcel.write(fileName, ImageData.class) // 指定文件导出的路径及样式
                .sheet("测试")           // 指定导出到哪个sheet工作簿
                .doWrite(Arrays.asList(imageData));
        // 导出操作 准备数据

    }
}
