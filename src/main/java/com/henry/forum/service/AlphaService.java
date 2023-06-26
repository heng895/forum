package com.henry.forum.service;

import com.henry.forum.dao.AlphaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");  //初始化后调用
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");   //销毁之前调用
    }

    public String find(){
        return alphaDao.select();
    }
}
