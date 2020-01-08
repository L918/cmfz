package com.baizhi.lzz.dao;

import com.baizhi.lzz.entity.Admin;
import tk.mybatis.mapper.additional.dialect.oracle.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;

public interface AdminDao extends Mapper<Admin>, DeleteByPrimaryKeyMapper<Admin>, InsertListMapper<Admin> {
}
