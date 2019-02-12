package com.learn.fly.consumerserver.server;

import com.learn.fly.consumerserver.fallback.HelloServerFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午6:20
 * @Description:
 * @Modified:
 */
@FeignClient(value = "hello-server", fallback = HelloServerFallback.class)
public interface HelloServer {

    @RequestMapping(value = "/hello/hello", method = RequestMethod.GET)
    String hello();
}
