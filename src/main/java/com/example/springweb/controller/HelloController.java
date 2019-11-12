package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Controller
public class HelloController {// 控制页面跳转,连接数据库
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);
//    JdbcTemplate jdbcTemplate;// TODO 测试数据库

    // 从主页跳转到任何页面
    @RequestMapping("/{page}")
    public String changePage(@PathVariable("page") String page) {
        infoLog("hello logging" + helloService.getUserList());// TODO 数据库
        infoLog("from index");
        return page;
    }

    // 在登录页面
    @RequestMapping("/login/{page}")
    public String login(GetLogin getLogin, @PathVariable("page") String page) {
//    public String login(GetLogin getLogin) {
//        String page = "";
        infoLog("page: " + page);
        infoLog("account: " + getLogin.account);
        return "login/" + page;
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

    public class GetLogin {
        public String account;
        public String password;

        public void setAccount(String account) {
            this.account = account;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
