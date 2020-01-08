package com.baizhi.lzz.dao;

import com.baizhi.lzz.entity.Chapter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ChapterDao extends Mapper<Chapter> , DeleteByIdListMapper<Chapter,String> {
}
