package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    String curUser = null;// 当前用户
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    // 从主页跳转到任何页面
    @RequestMapping("/{page}")
    public String changePage(@PathVariable("page") String page) {
        infoLog("request1: " + page);
        return page;
    }

    @RequestMapping("/enterprise/{page}")
    public String login(GetLogin getLogin, @PathVariable("page") String page, Model model) {
        infoLog("request2: " + page);// TODO 对于不同request,返回不同文件
        allUsers = helloService.getUserList();// 更新用户列表

        if (page.equals("home.html") || page.equals("home")) {
            String account = getLogin.account;
            String password = getLogin.password;
            infoLog(allUsers + "");
            infoLog("account: " + account);
            infoLog("password: " + password);

            if (account == null || password == null) {
                curUser = null;// TODO 清空登录信息
                return "redirect:/enterprise";// 不能够直接访问个人主页
            }

            for (int i = 0; i < allUsers.size(); i ++) {
                HelloUser tmpUser = allUsers.get(i);
                infoLog(tmpUser.getId() + ": " + tmpUser.getPassword());
                if (allUsers.get(i).getId().equals(account)) {// 存在用户
                    if (tmpUser.getPassword().equals(password)) {// 密码正确
                        curUser = tmpUser.getName();
                        infoLog("成功登陆");
                        return "/enterprise/home";// 登陆成功
                    } else {
                        curUser = null;// TODO 清空登录信息
                        infoLog("密码错误");
                        return "redirect:/enterprise?error=1";// TODO 密码错误
                    }
                }
            }
            curUser = null;// TODO 清空登录信息
            infoLog("账号不存在");
            return "redirect:/enterprise?error=2";// TODO 账号不存在
        } else {
            return "redirect:/enterprise/" + page;
        }

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
