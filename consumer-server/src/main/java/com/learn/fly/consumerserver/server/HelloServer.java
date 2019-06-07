package com.learn.fly.consumerserver.server;

import com.learn.fly.consumerserver.fallback.HelloServerFallback;
import com.learn.fly.consumerserver.transfer.server.HelloDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/hello/add")
    String add(HelloDTO helloDTO);
}
