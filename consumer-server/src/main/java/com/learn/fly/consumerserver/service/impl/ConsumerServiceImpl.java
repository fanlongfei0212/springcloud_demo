package com.learn.fly.consumerserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.learn.fly.consumerserver.model.Consumer;
import com.learn.fly.consumerserver.repository.ConsumerMapper;
import com.learn.fly.consumerserver.server.HelloServer;
import com.learn.fly.consumerserver.service.ConsumerService;
import com.learn.fly.consumerserver.transfer.server.HelloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:Fly
 * @Date:Create in 2019/6/7 下午10:35
 * @Description:
 * @Modified:
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Autowired
    private HelloServer helloServer;

    /**
     *@Author:Fly Created in 2019/6/7 下午10:51
     *@Description: 添加
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Consumer consumer) {

        this.save(consumer);
    }

    /**
     *@Author:Fly Created in 2019/6/7 下午11:42
     *@Description: 同时添加两个服务的值
     */
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String testLcn(HelloDTO helloDTO) {

        Consumer consumer = new Consumer();
        consumer.setName(helloDTO.getName());
        this.save(consumer);

        return "consumer:ok -> hello:" + helloServer.add(helloDTO);
    }


}
