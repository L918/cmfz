package com.baizhi.lzz.controller;

import com.baizhi.lzz.code.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@ResponseBody
public class CodeController {

    @RequestMapping("/getCode")
    public void test(HttpSession session, HttpServletResponse response) throws IOException {
        CreateValidateCode code = new CreateValidateCode();
        String code1 = code.getCode();
        code.write(response.getOutputStream());
        System.out.println(code1);
        session.setAttribute("code",code1);
    }
}
