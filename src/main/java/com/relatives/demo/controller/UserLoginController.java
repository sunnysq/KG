package com.relatives.demo.controller;

import com.relatives.demo.entity.user;
import com.relatives.demo.service.UserLoginService;
import com.sun.applet2.AppletParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    private AppletParameters map;

    @RequestMapping("/Login")
    public String login() {
        return "userLogin";
    }
    /**
     * @RequestParam将前台usename的值绑定到方法中的usename，也就是说通过前台的name的属性值(name=username)
     */
    @RequestMapping("/userLogin")
    public String userLogin(@RequestParam(value = "usename", required = false) String usename, @RequestParam(value = "password", required = false) String password, HttpServletRequest request, Map<String, Object> map,
                            HttpSession session) {
        user user = userLoginService.userLogin(usename, password);

        if (user != null) {                                                  //登录成功
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session
            return "index";
        } else  //输入错误，清空session，提示用户名密码错误
        {
            session.invalidate();
            map.put("msg", "用户名密码错误");
            return "userLogin";
        }
    }
}