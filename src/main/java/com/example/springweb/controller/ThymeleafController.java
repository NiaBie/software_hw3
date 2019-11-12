package com.example.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {

    @RequestMapping("/thymeleaf")
    public String hello(Model model) {
        infoLog("Thymeleaf");

        model.addAttribute("user_name", "Hello!");
        return "hello";
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }
}
