package com.learn.fly.consumerserver.fallback;

import com.learn.fly.consumerserver.server.HelloServer;
import com.learn.fly.consumerserver.transfer.server.HelloDTO;
import org.springframework.stereotype.Component;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午7:00
 * @Description: 服务降级
 * @Modified:
 */
@Component
public class HelloServerFallback implements HelloServer{


    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String add(HelloDTO helloDTO) {
        return "error";
    }
}
