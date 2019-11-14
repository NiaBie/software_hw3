package com.example.springweb.mapper;

import com.example.springweb.dao.UserDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user;")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "userName", column = "userName")
    })
    List<UserDetail> findAll();// 返回所有app详情

    @Insert("insert into user(uid, userName, password) values(#{uid}, #{userName}, #{password});")
    void insert(UserDetail userDetail);


    @Select("select * from user where uid = #{uid};")
    @Results({
            @Result(property = "uid",column = "uid"),
            @Result(property = "userName",column = "userName")
    })
    UserDetail getOne(String uid);

    @Update("update user set userName = #{userName}, password = #{password} where uid = #{uid};")
    void updateByID(UserDetail userDetail);// TODO UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

    @Delete("delete from user where uid = #{uid};")
    void deleteByID(String uid);// TODO DELETE FROM 表名称 WHERE 列名称 = 值
}