package com.henry.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication		//程序入口注解
//@MapperScan({"com.henry.forum.dao"})
public class ForumApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ForumApplication.class, args);
	}
}