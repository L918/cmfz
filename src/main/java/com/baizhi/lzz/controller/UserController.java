package com.baizhi.lzz.controller;

import com.baizhi.lzz.entity.Ug;
import com.baizhi.lzz.entity.User;
import com.baizhi.lzz.service.UserService;
import com.baizhi.lzz.util.HttpUtil;
import com.baizhi.lzz.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/showUser")
    public Map showUser(Integer page, Integer rows){
        System.out.println(userService.queryAll(page,rows));
        return userService.queryAll(page,rows);
    }


    @RequestMapping("/edit")
    public Map edit(User user, String oper, String[] id) {
        HashMap hashMap = new HashMap();
        if (oper.equals("add")) {
            user.setId(UUID.randomUUID().toString());
            user.setRigestDate(new Date());
            userService.insertUser(user);
            hashMap.put("userId", user.getId());
        }
        if (oper.equals("del")) {

        }
        if (oper.equals("edit")) {
            userService.updateUser(user);
            hashMap.put("userId", user.getId());
        }
        return hashMap;

    }
    @RequestMapping("showUserTime")
    public Map showUserTime() {
        Map map = userService.queryUserByTime();
        System.out.println(map);
        return map;
    }
    @RequestMapping("showAddress")
    public Map showAddress(){
        return  userService.queryUserAddress();
    }
    @RequestMapping("/upload")
    // MultipartFile url(上传的文件),String bannerId(头像Id 更新使用)
    public Map upload(MultipartFile cover, String userId, HttpSession session, HttpServletRequest request) {
        /*
            1. 相对路径 : /upload/img/XXX.jpg 优:方便处理 缺:耦合度较高,在网络环境下难以定位
            2. 网络路径 : http://localhost:9999/cmfz/upload/img/XXX.jpg 缺:处理麻烦(需要程序员手动处理) 优:定位,精准度较高
         */
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/user");
        //判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String http = HttpUtil.getHttp(cover,request,"/upload/user/");
        //文件上传 工具类
        //更新数据库信息
        User user = new User();
        user.setId(userId);
        user.setPhoto(http);
        userService.updateUser(user);
        return hashMap;
    }
    //2发送短信验证码，用户注册
    @RequestMapping("sendCode")
    public Map sendCode(String phone){
        String s = UUID.randomUUID().toString();
        String code = s.substring(0, 6);
        SmsUtil.send(phone,code);

//        Jedis jedis = new Jedis("192.168.131.106",7000);
//        jedis.set("phone_"+phone,code);
//        jedis.expire("phone_"+phone,60);

        // 将验证码保存值Redis  Key phone_186XXXX Value code 1分钟有效
        HashMap hashMap = new HashMap();
        hashMap.put("codeOne",code);
        hashMap.put("status","200");
        hashMap.put("message","短信发送成功");
        return hashMap;
    }
    //3注册
    @RequestMapping("userAdd")
    public  Map userAdd(String phone,String code,String codeOne){
        HashMap hashMap =  new HashMap();
        //取出存在 redis 中的验证码 codeOne
        if (code.equals(codeOne)){
         String id =   UUID.randomUUID().toString();
         User user = new User();
         user.setId(id);
         user.setPhone(phone);
        hashMap.put("user",user);
        }
        return hashMap;
    }
    //1用户登录
    @RequestMapping("login")
    public Map login(String phone,String password){
        HashMap hashMap = new HashMap();
        User user = userService.login(phone, password);
        if (user.getPhone()!=null){
            hashMap.put("user",user);
            hashMap.put("status","200");
        }else {
            hashMap.put("mession","手机号或密码错误");
        }
        return hashMap;
    }

    // 4补充个人信息接口
    @RequestMapping("userAddUpdate")
    public Map userAddUpdate(User user){
        Map hashMap = new HashMap();
        userService.updateUser(user);
        hashMap.put("user", user);
        return  hashMap;
    }
    //15. 修改个人信息
    @RequestMapping("userUpdate")
    public Map userUpdate(User user){
        HashMap hashMap = new HashMap();
        System.out.println(user);
        userService.updateUser(user);
        hashMap.put("status",200);
        hashMap.put("user",user);
        return hashMap;
    }
    //16、随机推荐5个好友----金刚道友
    @RequestMapping("userFive")
    public Map userFive(String id){
        HashMap hashMap = new HashMap();
        List<User> users = userService.queryFive(id);
        hashMap.put("status",200);
        hashMap.put("user",users);
        return hashMap;
    }


}