package com.learn.fly.consumerserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.fly.consumerserver.model.Consumer;
import com.learn.fly.consumerserver.transfer.server.HelloDTO;

/**
 * @Author:Fly
 * @Date:Create in 2019/6/7 下午10:34
 * @Description:
 * @Modified:
 */
public interface ConsumerService extends IService<Consumer>{

    void add(Consumer consumer);

    String testLcn(HelloDTO helloDTO);
}
