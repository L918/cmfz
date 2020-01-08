package com.baizhi.lzz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ug {
  @Id
  private String id;
  private String userId;
  private String guruId;
}
