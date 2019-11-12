package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
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
    List<HelloUser> allUsers;// 记录所有用户
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    // 从主页跳转到任何页面
    @RequestMapping("/{page}")
    public String changePage(@PathVariable("page") String page) {
        infoLog("request1: " + page);
        return page;
    }

    @RequestMapping("/enterprise/{page}")
    public String login(GetLogin getLogin, @PathVariable("page") String page, Map<String, Object> map) {
        infoLog("request2: " + page);// TODO 对于不同request,返回不同文件
        allUsers = helloService.getUserList();// 更新用户列表
        infoLog(allUsers + "");
        infoLog("account: " + getLogin.account);
        infoLog("password: " + getLogin.password);

        for (int i = 0; i < allUsers.size(); i ++) {
            infoLog(allUsers.get(i).getId() + ": " + allUsers.get(i).getPassword());
            if (allUsers.get(i).getId().equals(getLogin.account)) {// 存在用户
                if (allUsers.get(i).getPassword().equals(getLogin.password)) {// 密码正确
                    return "enterprise/" + page;// 登陆成功
                } else {
                    infoLog("密码错误");
                    map.put("result", "1");
                    return "redirect:/enterprise";// TODO 密码错误
                }
            }
        }
        infoLog("账号不存在");
        map.put("result", "2");
        return "redirect:/enterprise";// TODO 账号不存在
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

        public String getAccount() {
            return this.account;
        }

        public String getPassword() {
            return this.password;
        }
    }
}
