package com.baizhi.lzz.dao;

import com.baizhi.lzz.entity.City;
import com.baizhi.lzz.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {

    Integer queryUserByTime(@Param("sex") String sex,@Param("day") Integer day);


    List<City> queryUserByAddress(@Param("sex") String sex);


    //16、随机抽取数据
    List<User> queryAll(String uid);
}
