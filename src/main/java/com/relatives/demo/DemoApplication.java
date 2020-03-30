package com.relatives.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableNeo4jRepositories
@SpringBootApplication
@MapperScan("com.relatives.demo.dao")
public class DemoApplication {
    private final static Logger log = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

}
