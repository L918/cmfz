package com.baizhi.lzz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Guru {//上师表
  @Id
  private String id;
  private String name;
  private String photo;
  private String status;
  private String nickName;


}
