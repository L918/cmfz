package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    //8、展示功课
    List<Course> showCoursePage(String uid);
    //9、添加功课
    void insterCourse(Course course);
    //10、删除功课
    void deleteCourse(String id);

}
