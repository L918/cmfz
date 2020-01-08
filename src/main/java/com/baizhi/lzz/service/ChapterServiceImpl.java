package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.ChapterDao;
import com.baizhi.lzz.entity.Album;
import com.baizhi.lzz.entity.Chapter;
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
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDao chapterDao;

    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Map queryAllChapter(Integer page, Integer rows, String albumId) {
        HashMap map = new HashMap();
        map.put("page",page);
        int offser = (page-1)*rows;
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        int records = chapterDao.selectCount(null);
        List<Chapter> list = chapterDao.selectByRowBounds(null, new RowBounds(offser, rows));
        map.put("total",records%rows == 0?records/rows:records/rows+1);
        map.put("records",records);
        map.put("rows",list);
        return map;
    }

    @Override
    public void insertOne(Chapter chapter) {
       chapterDao.insert(chapter);
    }

    @Override
    public void updateOne(Chapter chapter) {
        chapterDao.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public void deleteOne(String[] id) {
        chapterDao.deleteByIdList(Arrays.asList(id));

    }

}
