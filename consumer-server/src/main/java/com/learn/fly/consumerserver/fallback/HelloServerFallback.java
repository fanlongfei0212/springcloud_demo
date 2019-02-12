package com.learn.fly.consumerserver.fallback;

import com.learn.fly.consumerserver.server.HelloServer;
import org.springframework.stereotype.Component;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午7:00
 * @Description: 服务降级
 * @Modified:
 */
@Component
public class HelloFallback implements HelloServer{


    @Override
    public String hello() {
        return "error";
    }
}
