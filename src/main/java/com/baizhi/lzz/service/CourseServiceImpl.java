package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.CourseDao;
import com.baizhi.lzz.entity.Course;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements  CourseService {
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Course> showCoursePage(String uid) {
        Course course = new Course();
        course.setUserId(uid);
        return courseDao.select(course);
    }

    @Override
    public void insterCourse(Course course) {
        courseDao.insert(course);
    }

    @Override
    public void deleteCourse(String id) {
        courseDao.deleteByPrimaryKey(id);
    }
}
