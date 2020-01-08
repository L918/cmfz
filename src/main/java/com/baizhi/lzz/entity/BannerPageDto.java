package com.baizhi.lzz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerPageDto implements Serializable {
    private Integer page;//当前页数
    private Integer total;//总页数
    private Integer records;//总条数
    private List<Banner> rows;//实体对象
    private List<Album> arows;//实体对象
    private List<Chapter> crows;//实体对象
}
