package com.baizhi.lzz.dao;


import com.baizhi.lzz.entity.Banner;
import tk.mybatis.mapper.additional.dialect.oracle.InsertListMapper;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerDao extends Mapper<Banner>, DeleteByIdListMapper<Banner,String>, InsertListMapper<Banner> {
    List<Banner> queryBannersByTime();
}
