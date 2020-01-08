package com.baizhi.lzz.controller;

import com.baizhi.lzz.entity.Course;
import com.baizhi.lzz.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;
    //8、展示功课
    @RequestMapping("showCoursePage")
   public Map showCoursePage(String uid){
        HashMap hashMap = new HashMap();
        List<Course> list = courseService.showCoursePage(uid);
        hashMap.put("status",200);
        hashMap.put("option",list);
        return hashMap;
    }
    //9、添加功课
    @RequestMapping("insterCourse")
    public Map insterCourse(String uid ,String title){
        HashMap hashMap = new HashMap();
        Course course = new Course();
        course.setUserId(uid);
        course.setCreateDate(new Date());
        course.setTitle(title);
        course.setId(UUID.randomUUID().toString());
        courseService.insterCourse(course);
        hashMap.put("courseId",course.getId());
        hashMap.put("status",200);
        hashMap.put("option",course);
        return hashMap;
    }
    //10、删除功课
    @RequestMapping("deleteCourse")
    public Map deleteCourse(String uid,String id){
        HashMap hashMap = new HashMap();
        courseService.deleteCourse(id);
        hashMap.put("status",200);
        hashMap.put("uid",uid);
        return hashMap;
    }


}
