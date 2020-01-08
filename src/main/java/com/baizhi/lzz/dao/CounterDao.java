package com.baizhi.lzz.dao;

import com.baizhi.lzz.entity.Counter;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
public interface CounterDao extends Mapper<Counter>{
    //根据用户id查所有
   List<Counter> selectId(String uid, String id);

}
