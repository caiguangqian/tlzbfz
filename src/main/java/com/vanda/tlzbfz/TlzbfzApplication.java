package com.vanda.tlzbfz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.vanda.tlzbfz.mapper")
public class TlzbfzApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlzbfzApplication.class, args);
    }

}
