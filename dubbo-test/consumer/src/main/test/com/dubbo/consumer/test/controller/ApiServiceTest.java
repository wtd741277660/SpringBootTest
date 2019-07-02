package com.dubbo.consumer.test.controller;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.dubbo.api.service.ApiService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
//@EnableDubboConfiguration
@ImportResource(locations= {"classpath:entrance.xml"})
public class ApiServiceTest {

    @Reference
    private ApiService apiService;

    @Test
    public void testApiService(){
        String result = apiService.testApi("hehe");
        System.out.println(":::::::" + result);
    }

}
