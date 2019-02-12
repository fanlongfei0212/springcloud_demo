package com.learn.fly.consumerserver.controller;

import com.learn.fly.consumerserver.server.HelloServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午6:19
 * @Description:
 * @Modified:
 */
@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @Autowired
    private HelloServer helloServer;

    @RequestMapping(value = "/hello")
    public String hello(){

        return helloServer.hello();
    }
}
