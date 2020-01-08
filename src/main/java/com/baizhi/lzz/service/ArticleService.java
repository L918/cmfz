package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Article;

import java.util.Map;

public interface ArticleService {

    //根据上师ID分页
    Map selectIdAll(Integer page,Integer rows,String guruId);

    //分页查所有
    Map selectAll(Integer page,Integer rows);

    //添加
    void insertArticle(Article article);

    //删除
    void delectArticle(String[] articleId);

    //修改
    void updateArticle(Article article);
    //6、文章详情        id 文章id
   Map articleTitle(String id);
}
