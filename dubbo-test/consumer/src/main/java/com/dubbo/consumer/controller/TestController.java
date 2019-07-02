package com.dubbo.consumer.controller;

import com.dubbo.api.service.ApiService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ApiService apiService;

    @RequestMapping(value="/test")
    @ResponseBody
    public String testApiService(@RequestParam("name") String name){
        String result = apiService.testApi(name);
        return result;
    }
}
