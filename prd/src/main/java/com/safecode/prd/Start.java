package com.safecode.prd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//表名此类是程序的入口
@SpringBootApplication
public class Start {
	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
		//去掉斑马线
		//SpringApplication application=new SpringApplication(Demo.class);
		//application.setBannerMode(Mode.OFF);
		//application.run(args);
	}
}
