package com.dubbo.producer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.api.service.ApiService;

@Service
public class ApiServiceProducer implements ApiService {

    @Override
    public String testApi(String name) {
        System.out.println("receive name:" + name);
        return name + "success";
    }
}
