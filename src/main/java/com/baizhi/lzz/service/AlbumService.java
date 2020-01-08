package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Album;

import java.util.Map;

public interface AlbumService {
    //查询所有专辑
    Map queryAll(Integer page,Integer rows);
    //7、专辑详情
    Map albumTitle(String id);
    //修改专辑信息
    void updateOne(Album album);

    //添加专辑
    void insertOne(Album album);

    //删除专辑
    void deleteOne(String[] id);
}
