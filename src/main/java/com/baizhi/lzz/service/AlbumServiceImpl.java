package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.AlbumDao;
import com.baizhi.lzz.entity.Album;
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
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumDao albumDao;
    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Map queryAll(Integer page, Integer rows) {
        HashMap map = new HashMap();
        map.put("page",page);
        int offset = (page-1)*rows;
        int records = albumDao.selectCount(null);
        List<Album> list = albumDao.selectByRowBounds(null, new RowBounds(offset, rows));
        map.put("total", records%rows == 0?records/rows:records/rows+1);
        map.put("records",records);
        map.put("rows",list);
        return map;
    }
    //7、专辑详情
    @Override
    public Map albumTitle(String id) {
        Album album = albumDao.selectByPrimaryKey(id);
        HashMap hashMap = new HashMap();
        hashMap.put("album",album);
        return hashMap;
    }


    @Override
    public void updateOne(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }

    @Override
    public void insertOne(Album album) {
        albumDao.insert(album);
    }

    @Override
    public void deleteOne(String[] id) {
        albumDao.deleteByIdList(Arrays.asList(id));
    }
}
