package com.baizhi.lzz.dao;

import tk.mybatis.mapper.common.Mapper;
import com.baizhi.lzz.entity.Course;

import java.util.List;

public interface CourseDao extends Mapper<Course> {
    //根据用户id查所有
    List<Course> selectId(String uid);
}
