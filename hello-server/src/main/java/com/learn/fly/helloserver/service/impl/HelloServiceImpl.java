package com.learn.fly.helloserver.service.impl;

import com.learn.fly.helloserver.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午5:53
 * @Description:
 * @Modified:
 */
@Service
public class HelloServiceImpl implements HelloService{

    /**
     *@Author:Fly Created in 2019/2/12 下午5:54
     *@Description:
     */
    @Override
    public String hello() {

        return "HelloWord";
    }
}
