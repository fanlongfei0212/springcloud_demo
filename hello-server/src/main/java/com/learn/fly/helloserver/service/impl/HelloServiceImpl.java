package com.learn.fly.helloserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.learn.fly.helloserver.model.Hello;
import com.learn.fly.helloserver.repository.HelloMapper;
import com.learn.fly.helloserver.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/12 下午5:53
 * @Description:
 * @Modified:
 */
@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper, Hello> implements HelloService{

    /**
     *@Author:Fly Created in 2019/2/12 下午5:54
     *@Description:
     */
    @Override
    public String hello() {

        return "HelloWord";
    }

    /**
     *@Author:Fly Created in 2019/6/7 下午10:50
     *@Description: 添加
     */
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Hello hello) {

        int a = 1/0;

        this.save(hello);
    }
}
