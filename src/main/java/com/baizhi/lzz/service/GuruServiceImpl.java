package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.GuruDao;
import com.baizhi.lzz.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    GuruDao guruDao;
    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Map queryAll(Integer page, Integer rows) {
        HashMap map = new HashMap();
        map.put("page",page);
        int offset = (page)*rows;
        int records = guruDao.selectCount(null);
        List<Guru> list = guruDao.selectByExampleAndRowBounds(null, new RowBounds(offset, rows));
        map.put("total",records%rows==0?records/rows:records/rows+1);
        map.put("record",records);
        map.put("rows",list);
        return map;
    }

    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
   public List<Guru> selectAll(){
        List<Guru> list = guruDao.selectAll();
        return  list;
    }





    @Override
    public void insertGuru(Guru guru) {
        guruDao.insert(guru);
    }

    @Override
    public void delectGuru(String[] guruId) {
        guruDao.deleteByIdList(Arrays.asList(guruId));
    }

    @Override
    public void updateGuru(Guru guru) {
        guruDao.updateByPrimaryKeySelective(guru);
    }
}
