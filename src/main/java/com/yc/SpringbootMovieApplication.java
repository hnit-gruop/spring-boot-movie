package com.yc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yc.dao.MovieMapper;

@SpringBootApplication
@MapperScan("com.yc.dao")
public class SpringbootMovieApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMovieApplication.class, args);
	}
}
