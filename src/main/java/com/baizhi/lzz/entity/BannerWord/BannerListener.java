package com.baizhi.lzz.entity.BannerWord;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baizhi.lzz.dao.BannerDao;
import com.baizhi.lzz.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/*
    1. 创建一个Listener类 继承 AnalysisEventListener<实体类>
    2. 重写方法
    invoke : 读取每行数据后会执行的方法
    doAfterAllAnalysed: 所有数据读取完毕后执行的方法
 */
public class BannerListener extends AnalysisEventListener<Banner> {
  List<Banner> list =   new ArrayList<>();
    // DemoData 针对每行数据 进行的实体类封装
    @Override
    public void invoke(Banner banner, AnalysisContext analysisContext) {
      list.add(banner);
        System.out.println(banner);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(list);
    }



}
