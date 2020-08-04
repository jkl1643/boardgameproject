package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JavaConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public DriverManagerDataSource datasource() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost/midterm?characterEncoding=utf8&serverTimezone=UTC");
        datasource.setUsername("root");
        datasource.setPassword("1234");
        return datasource;
    }
}