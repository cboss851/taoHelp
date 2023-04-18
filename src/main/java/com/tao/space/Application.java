package com.tao.space;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@MapperScan(basePackages = {"com.tao.space.mapper", "com.tao.space.dal.mapper"})
@SpringBootApplication(scanBasePackages = {"com.tao.space", "com.tao.commons", "com.tao.generator", "com.tao.datat"})
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run();
    }
}