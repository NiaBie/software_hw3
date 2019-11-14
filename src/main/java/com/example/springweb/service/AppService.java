package com.example.springweb.service;

import com.example.springweb.dao.AppDetail;
import com.example.springweb.mapper.AppMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppService {
    @Resource
    private AppMapper appMapper;

    public List<AppDetail> getUserList() {
        List<AppDetail> list = appMapper.findAll();
        return list;
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

}
