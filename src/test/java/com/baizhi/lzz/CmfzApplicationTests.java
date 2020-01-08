package com.baizhi.lzz;

import com.baizhi.lzz.dao.*;
import com.baizhi.lzz.entity.*;
import com.baizhi.lzz.service.AlbumService;
import com.baizhi.lzz.service.ArticleService;
import com.baizhi.lzz.service.CourseService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {
    @Autowired
    AdminDao adminDao;
    @Autowired
    CounterDao counterDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
   @Autowired
    CourseService courseService;




    @Test
    public  void contextLoads() {
        //List<Admin> list = adminDao.selectAll();
        //System.out.println(select);
//        for (Admin ad:list){
//            System.out.println(ad);
//        }
        List<Admin> admins = adminDao.selectByRowBounds(null, new RowBounds(0, 5));
        System.out.println(admins);
        System.out.println(admins+"++++++++++++++++++++++++");
        System.out.println("321654");
    }
    @Autowired
    AlbumDao albumDao;
    @Autowired
    AlbumService albumService;

    @Test
    public void t7(){
        //List<Album> list = albumDao.selectByRowBounds(null, new RowBounds(1, 2));
        //System.out.println(list);
        List<Album> list1 = albumDao.selectAll();
        System.out.println(list1);
    }
    @Autowired
    ArticleService articleService;
    @Test
    public void name() {
        Map map = articleService.selectIdAll(1, 5, "1");
        System.out.println(map);
    } @Test
    public void nam123e() {
//        List<Course> list  = courseDao.selectId("1");
////        List<Course> list = courseService.showCoursePage("2");
//        List<Course> list1 = courseDao.selectAll();
//        System.out.println(courseDao.selectId("1"));
//        System.out.println("-----------------");
//        System.out.println(map1);
        Course course = new Course();
        course.setUserId("1");
        List<Course> select = courseDao.selectId("2");

        System.out.println(select);


    }
}
