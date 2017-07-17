package com.rain.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;

/**
 * Created by rain on 17-7-13.
 */
@SpringBootApplication
@EnableTransactionManagement
public class MainApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    //@Bean
    //public PlatformTransactionManager txManager(DataSource dataSource) {
    //    return new DataSourceTransactionManager(dataSource);
   // }


    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }
    public static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
    }
}
