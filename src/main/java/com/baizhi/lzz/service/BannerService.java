package com.baizhi.lzz.service;

import com.baizhi.lzz.entity.Banner;
import com.baizhi.lzz.entity.BannerPageDto;

import java.util.List;

public interface BannerService {
    //根据上传时间，展示最新照片
    List<Banner> queryBannersByTime();

    //展示所有轮播图信息
    BannerPageDto queryByPage(int page, int rows);

    //修改轮播图信息
    void updataBanner(Banner banner);

    //添加轮播图
    void addBanner(Banner banner);

    //删除轮播图
    void deleteBanner(String[] id);

}
