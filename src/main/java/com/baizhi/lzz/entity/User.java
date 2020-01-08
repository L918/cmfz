package com.baizhi.lzz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  @Id
  private String id;
  private String phone;//手机号
  private String password;//密码
  private String salt;//盐值
  private String status;//状态
  private String photo;//头像
  private String name;//法名
  private String nickName;//名字
  private String sex;//性别
  private String sign;//签名
  private String location;//地址
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rigestDate;//注册时间
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date lastLogin;//最后登录时间

}
