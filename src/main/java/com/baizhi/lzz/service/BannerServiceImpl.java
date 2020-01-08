package com.baizhi.lzz.service;


import com.baizhi.lzz.dao.BannerDao;
import com.baizhi.lzz.entity.Banner;
import com.baizhi.lzz.entity.BannerPageDto;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerDao bannerDao;


    @Override
    public List<Banner> queryBannersByTime() {
        return  bannerDao.queryBannersByTime();
    }

    @Override
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public BannerPageDto queryByPage(int pageSize, int rows) {
        BannerPageDto dto = new BannerPageDto();
        int offset = (pageSize-1)*rows;
        List<Banner> list = bannerDao.selectByRowBounds(new Banner(), new RowBounds(offset, rows));
        dto.setRows(list);
        int totalCount = bannerDao.selectCount(new Banner());
        dto.setRecords(totalCount);
        dto.setPage(pageSize);
        dto.setTotal(totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1);// 设置总页
        return dto;
    }

    @Override
    public void updataBanner(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void addBanner(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void deleteBanner(String[] id) {
        bannerDao.deleteByIdList(Arrays.asList(id));
    }
}