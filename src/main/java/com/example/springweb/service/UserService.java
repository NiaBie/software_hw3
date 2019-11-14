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

    /*public void InsertUser(HelloUser helloUser) {
        helloMapper.insert(helloUser);
        System.out.println("After insert:" + helloMapper.findAll());
    }*/
    public void InsertUser(Map<String, String> params) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetail userDetail = objectMapper.convertValue(params, UserDetail.class);
        userMapper.insert(userDetail);
    }


    public UserDetail getOne(String uid) {
        // HelloUser result = new HelloUser();
        UserDetail result = userMapper.getOne(uid);
        System.out.println("getOne:" + result);
        if (result == null)
        {
            result = new UserDetail();// 索引为空的时候，返回null，需要这时候对其getId,getName就会出错。
        }
        System.out.println(result.toString());
        return result;
    }

    /*public void UpdateByID(HelloUser helloUser) {
        helloMapper.updateByID(helloUser);
        System.out.println("After update:" + helloMapper.getOne(helloUser.getId()));
    }*/
    public void UpdateByID(Map<String, String> params) {
        String uid = params.get("uid");
        // Long recordId = Long.parseLong(params.get("recordId"));
        // ObjectMapper objectMapper = new ObjectMapper();
        // HelloUser helloUser = objectMapper.convertValue(params, HelloUser.class);
        // helloMapper.updateByID(helloUser);
        UserDetail temp = userMapper.getOne(uid);
        if(params.get("name") != null)
            temp.setUserName(params.get("name"));
        if(params.get("password") != null)
            temp.setPassword((params.get("password")));
        userMapper.updateByID(temp);
    }

    public void DeleteByID(String uid) {
        userMapper.deleteByID(uid);
        System.out.println("After Delete:" + userMapper.getOne(uid));
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

}
