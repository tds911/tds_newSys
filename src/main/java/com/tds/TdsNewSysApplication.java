package com.tds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TdsNewSysApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(TdsNewSysApplication.class, args);
        System.out.println("**********启动成功**********");
    }

}
