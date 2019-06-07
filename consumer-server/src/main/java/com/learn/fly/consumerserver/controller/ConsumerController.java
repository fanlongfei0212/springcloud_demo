package com.learn.fly.consumerserver.controller;

import com.learn.fly.consumerserver.model.Consumer;
import com.learn.fly.consumerserver.server.HelloServer;
import com.learn.fly.consumerserver.service.ConsumerService;
import com.learn.fly.consumerserver.transfer.server.HelloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/hello")
    public String hello(){

        return helloServer.hello();
    }

    /**
     *@Author:Fly Created in 2019/6/7 下午10:53
     *@Description: 添加
     */
    @PostMapping(value = "/add")
    public String add(@RequestBody Consumer consumer){

        consumerService.add(consumer);

        return "ok";
    }

    /**
     *@Author:Fly Created in 2019/6/7 下午11:33
     *@Description: 测试LCN，同时添加两个服务的内容
     */
    @PostMapping(value = "/test/lcn")
    public String testLcn(@RequestBody HelloDTO helloDTO){

        return consumerService.testLcn(helloDTO);
    }
}
