package com.baizhi.lzz.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringImageConverter;
import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.lzz.entity.BannerWord.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {//轮播图表
  @Id
  @ExcelProperty(value = "ID",index = 0)
  private String id;
  @ExcelProperty(value = "标题",index = 1)
  private String title;
  @ExcelProperty(converter = ImageConverter.class,value = "图片",index = 2)
  private String url;
  @ExcelProperty(value = "超链接",index = 3)
  private String href;
  @ExcelProperty(value = "时间",index = 4)
  @JSONField(format="yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createDate;
  @ExcelProperty(value = "描述",index = 5)
  private String description;
  @ExcelProperty(value = "状态",index = 6)
  private String status;
}
