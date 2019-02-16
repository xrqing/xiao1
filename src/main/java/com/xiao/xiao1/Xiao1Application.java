package com.xiao.xiao1;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.xiao.xiao1.mapper")
public class Xiao1Application {

    public static void main(String[] args) {
        SpringApplication.run(Xiao1Application.class, args);
    }

}

