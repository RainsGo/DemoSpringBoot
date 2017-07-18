package com.rain.demo;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;

/**
 * Created by rain on 17-7-13.
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@Configuration
public class MainApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    // For Undertow
    // 在@Configuration的类中添加@bean
    @Bean
    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {

        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();

        // 这里也可以做其他配置
        factory.addBuilderCustomizers(builder -> builder
                .addHttpListener(8880, "localhost")
                .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Undertow Running ...");
                    }
                }));

        return factory;
    }


    // For Jetty
    /*
    @Bean
    public JettyEmbeddedServletContainerFactory  jettyEmbeddedServletContainerFactory() {
        JettyEmbeddedServletContainerFactory jettyContainer =
                new JettyEmbeddedServletContainerFactory();

        jettyContainer.setPort(9000);
        jettyContainer.setContextPath("/springbootapp");
        return jettyContainer;
    }
    */

    public static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
        /*
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Undertow Running ...");
                    }
                }).build();
        server.start();
        */
    }
}
