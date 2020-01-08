package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Album;
import com.baizhi.lzz.entity.BannerPageDto;
import com.baizhi.lzz.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    //分页查询所有音频
    Map queryAllChapter(@Param("page") Integer page, @Param("rows") Integer rows, @Param("albumId")String albumId);


    //添加音频
    void insertOne(Chapter chapter);

    //修改音频信息
    void updateOne(Chapter chapter);

    //删除音频
    void deleteOne(String[] id);
}
