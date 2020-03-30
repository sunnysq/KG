package com.relatives.demo.service;

import com.relatives.demo.dao.userMapper;
import com.relatives.demo.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    @Autowired
    private userMapper usermapper;
    //用户登录
    public user userLogin(String usename, String password){
        user user = usermapper.userlogin(usename,password);
        return user;
    }
}
