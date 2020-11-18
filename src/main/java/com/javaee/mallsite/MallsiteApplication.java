package com.javaee.mallsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.javaee.mallsite.dao")
public class MallsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallsiteApplication.class, args);
    }

}
