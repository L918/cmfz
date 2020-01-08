package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.CounterDao;
import com.baizhi.lzz.dao.CourseDao;
import com.baizhi.lzz.entity.Counter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CounterServiceImpl implements CounterService {
    @Autowired
    CounterDao counterDao;


    @Override
    public List showPage(String uid,String id) {
        Counter counter = new Counter();
        counter.setId(id);
        counter.setUserId(uid);
        return counterDao.select(counter);
    }

    @Override
    public void insterCounter(Counter counter) {
        counterDao.insert(counter);
    }

    @Override
    public void deleteCounter(String id) {
        counterDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateCounter(Counter counter) {
        counterDao.updateByPrimaryKeySelective(counter);
    }
}
