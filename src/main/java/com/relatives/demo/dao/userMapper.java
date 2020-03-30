package com.relatives.demo.dao;

import com.relatives.demo.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface userMapper {

    //@Select("Select usename, password FROM user WHERE usename = #{usename} AND password = #{password}")
    public user userlogin(@Param("usename") String usename, @Param("password") String password);

}