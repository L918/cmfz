package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //用户登录
    User login(String phone,String password);
    //查所有
    Map queryAll(Integer page, Integer rows);
    //添加
    void insertUser(User user);
    //修改
    void updateUser(User user);

    //根据时间，性别，查询
    Map queryUserByTime();

    //根据地区，性别，查询
    Map queryUserAddress();

    //16、随机抽取数据
    List<User> queryFive(String uid);

}
