package org.example;
//springboot的启动类

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//在 Spring Boot 启动类中添加 @MapperScan 注解，扫描 Mapper 文件夹
//springboot启动类就会知道映射文件在mapper里面
@SpringBootApplication//使用这个标志为启动类
@MapperScan("org.example.mapper")
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
