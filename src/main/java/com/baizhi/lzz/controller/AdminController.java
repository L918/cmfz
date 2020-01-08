package com.baizhi.lzz.controller;

import com.baizhi.lzz.entity.Admin;
import com.baizhi.lzz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @ResponseBody
    @RequestMapping("/login")
    public String login(Admin admin,String code,HttpSession session){

        String mis = null;
        boolean A = adminService.loginAdmin(admin);
        String code1 = (String) session.getAttribute("code");
        if(code1.equals(code)){
            if(A){
                return null;
            }else{
                return mis="账号或密码错误！！！";
            }
        }
        return mis="验证码错误";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        session.removeAttribute("admin");
        return "login";
    }
}
