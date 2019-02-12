package com.learn.fly.helloserver.controller;

import com.learn.fly.helloserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午5:52
 * @Description: HelloController
 * @Modified:
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     *@Author:Fly Created in 2019/2/12 下午5:55
     *@Description:
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){

        return helloService.hello();
    }
}
