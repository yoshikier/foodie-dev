package com.yoshiker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描 mybatis 通用mapper所在的包
@MapperScan(basePackages = "com.yoshiker.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.yoshiker", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
