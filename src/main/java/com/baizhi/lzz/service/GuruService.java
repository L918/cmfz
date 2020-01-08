package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    //分页查所有
    Map queryAll(Integer page,Integer rows);

    //查所有
    List<Guru> selectAll();

    //添加
    void insertGuru(Guru guru);

    //删除
    void delectGuru(String[] guruId);

    //修改
    void updateGuru(Guru guru);

}
