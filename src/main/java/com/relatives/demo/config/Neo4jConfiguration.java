package com.relatives.demo.config;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfiguration {

    //    @Bean
//    public SessionFactory sessionFactory() {
//        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
//                .uri("bolt://localhost:7687")
//                .credentials("neo4j", "neo4j")
//                .build();
//        SessionFactory sessionFactory=new SessionFactory(configuration, "com.relatives.demo.entity");
//        return sessionFactory;
//    }
    @Bean(name = "driver")
    public Driver initDriver() {
        Driver driver;
        try {
            //neo4j地址 账号 密码
            String url = "bolt://localhost:7687";
            String username = "neo4j";
            String password = "neo4j";
            driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

//    @Bean
//    public Neo4jTransactionManager transactionManager() {
//        return new Neo4jTransactionManager(sessionFactory());
//    }

}