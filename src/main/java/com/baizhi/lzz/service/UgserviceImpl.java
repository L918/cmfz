package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.UgDao;
import com.baizhi.lzz.entity.Ug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UgserviceImpl implements Ugservice {
    @Autowired
    UgDao ugDao;

    @Override
    public void addUg(Ug ug) {
        ugDao.insert(ug);
    }
}
