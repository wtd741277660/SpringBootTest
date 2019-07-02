package com.dubbo.producer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//在启动类中添加xml入口配置文件，在这个文件中切换dubbo和hsf的配置文件
@ImportResource(locations= {"classpath:entrance.xml"})
public class ProducerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
        System.out.println("----------DONE---------------");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProducerApplication.class);
    }
}
