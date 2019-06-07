package com.learn.fly.helloserver.controller;

import com.learn.fly.helloserver.model.Hello;
import com.learn.fly.helloserver.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *@Author:Fly Created in 2019/6/7 下午10:47
     *@Description: 添加
     */
    @PostMapping(value = "/add")
    public String add(@RequestBody Hello hello){

        helloService.add(hello);

        return "ok";
    }
}
