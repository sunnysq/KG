package com.relatives.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      //登录成功之后应该有用户的session session_user
        Object loginUser=request.getSession().getAttribute("session_user");
        if(loginUser==null){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("userLogin.html").forward(request,response);
            return false;
        }else{
            return true;
        }
    }

}
