package com.example.springweb.service;

import com.example.springweb.dao.UserDetail;
import com.example.springweb.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<UserDetail> getUserList() {
        List<UserDetail> list = userMapper.findAll();
        return list;
    }

    public void addUser(UserDetail userDetail) {
        userMapper.insert(userDetail);
    }


    public UserDetail getByUid(String uid) {
        // HelloUser result = new HelloUser();
        UserDetail result = userMapper.getOne(uid);
        System.out.println("getOne: " + result);
        if (result == null)
        {
            result = new UserDetail();// 索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        System.out.println("getOne: " + result.toString());
        return result;
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

}
