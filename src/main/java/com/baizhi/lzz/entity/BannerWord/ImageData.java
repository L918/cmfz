package com.baizhi.lzz.entity.BannerWord;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageData {
    /**
     * 如果string类型 必须指定转换器，string默认转换成string
     * 绝对路径 继承StringImageConverter 重写 写入方法
     */
    @ExcelProperty(value = "图片",converter = ImageConverter.class)
    private String string;


}
