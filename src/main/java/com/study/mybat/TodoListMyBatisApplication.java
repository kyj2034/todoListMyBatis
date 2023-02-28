package com.study.mybat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.study.mybat.mapper"})
public class TodoListMyBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListMyBatisApplication.class, args);
	}

}