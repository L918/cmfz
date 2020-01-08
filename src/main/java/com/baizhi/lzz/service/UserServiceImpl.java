package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.UserDao;
import com.baizhi.lzz.entity.City;
import com.baizhi.lzz.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    //用户登录
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public User login(String phone,String password){
        User user = userDao.selectByPrimaryKey(phone);
        if (password!=null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return new User();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Map queryAll(Integer page, Integer rows) {
        HashMap map = new HashMap();
        map.put("page",page);
        int offset = (page-1)*rows;
        int records = userDao.selectCount(null);
        List<User> list = userDao.selectByRowBounds(null, new RowBounds(offset, rows));
        map.put("total",records%rows==0?records/rows:records/rows+1);
        map.put("records",records);
        map.put("rows",list);
        return map;
    }

    @Override
    public void insertUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public Map queryUserByTime() {
        HashMap hashMap = new HashMap();
        ArrayList manlist = new ArrayList();
        manlist.add(userDao.queryUserByTime("1",1));
        manlist.add(userDao.queryUserByTime("1",7));
        manlist.add(userDao.queryUserByTime("1",30));
        manlist.add(userDao.queryUserByTime("1",365));
        ArrayList womenlist = new ArrayList();
        womenlist.add(userDao.queryUserByTime("0",1));
        womenlist.add(userDao.queryUserByTime("0",7));
        womenlist.add(userDao.queryUserByTime("0",30));
        womenlist.add(userDao.queryUserByTime("0",365));
        hashMap.put("man",manlist);
        hashMap.put("women",womenlist);
        return hashMap;
    }

    @Override
    public Map queryUserAddress() {
        HashMap hashMap = new HashMap();
        List<City> manlist = userDao.queryUserByAddress("1");
        List<City> womenlist = userDao.queryUserByAddress("0");
        hashMap.put("man",manlist);
        hashMap.put("women",womenlist);
        return hashMap;
    }

    @Override
    public List<User> queryFive(String uid) {
        List<User> users = userDao.queryAll(uid);
        return users;
    }

}
