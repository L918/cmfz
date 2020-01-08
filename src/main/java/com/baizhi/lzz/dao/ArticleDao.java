package com.baizhi.lzz.dao;

import com.baizhi.lzz.entity.Article;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleDao extends Mapper<Article>, DeleteByIdListMapper<Article,String> {

    List<Article> selectIdAll(Integer page, Integer rows,String guruId);
}
