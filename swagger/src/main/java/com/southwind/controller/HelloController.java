package com.southwind.controller;


import com.southwind.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@ApiOperation("hello控制类")
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello boy";
    }
    //只要我们的接口中，返回值中存在实体类，他就会被扫描的swagger中
    @PostMapping(value = "/user")
    public User user(@ApiParam("用户信息") User user){
        return user;
    }
    @GetMapping("/hello1")
    public String hello1(@ApiParam("用户名") String name){
        return "hello boy"+name;
    }
}
