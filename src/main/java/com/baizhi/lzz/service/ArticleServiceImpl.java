package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.ArticleDao;
import com.baizhi.lzz.entity.Article;
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
public class ArticleServiceImpl implements  ArticleService{

    @Autowired
    ArticleDao articleDao;

    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Map selectIdAll(Integer page, Integer rows, String guruId) {
        HashMap map = new HashMap();
        map.put("page",page);
        int offset = (page-1)*rows;
        int records=articleDao.selectCount(null);
        List<Article> list = articleDao.selectIdAll(offset, rows, guruId);
        map.put("total",records%rows==0?records/rows:records/rows+1);
        map.put("records",records);
        map.put("rows",list);
        return map;
    }

    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public Map selectAll(Integer page, Integer rows) {
        HashMap map = new HashMap();
        map.put("page",page);
        int offset = (page-1)*rows;
        int records=articleDao.selectCount(null);
        List<Article> list = articleDao.selectByExampleAndRowBounds(null, new RowBounds(offset, rows));
        map.put("total",records%rows==0?records/rows:records/rows+1);
        map.put("records",records);
        map.put("rows",list);
        return map;
    }

    @Override
    public void insertArticle(Article article) {
    articleDao.insert(article);
    }

    @Override
    public void delectArticle(String[] articleId) {
       articleDao.deleteByIdList(Arrays.asList(articleId));
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    //6、文章详情        uid 用户id，id 文章id
    public Map articleTitle(String id){
        Article article = articleDao.selectByPrimaryKey(id);
        HashMap hashMap = new HashMap();
        hashMap.put("article",article);
        return hashMap;
    }


}
