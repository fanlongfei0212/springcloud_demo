package com.learn.fly.helloserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.fly.helloserver.model.Hello;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午5:53
 * @Description:
 * @Modified:
 */
public interface HelloService extends IService<Hello>{

    String hello();

    void add(Hello hello);
}
