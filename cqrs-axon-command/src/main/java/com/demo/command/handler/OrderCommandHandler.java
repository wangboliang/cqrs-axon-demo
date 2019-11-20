package com.demo.command.handler;

import com.demo.api.command.ConfirmOrderCommand;
import com.demo.api.command.CreateOrderCommand;
import com.demo.command.aggregate.OrderAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 命令处理器CommandHandler
 * 一个命令只可以有一个命令处理器
 * </p>
 *
 * @author wangliang
 * @since 2017/12/01
 */
@Slf4j
@Component
public class OrderCommandHandler {

    @Autowired
    private Repository<OrderAggregate> repository;
    @Autowired
    private EventBus eventBus;

    //step2.监听创建订单指令
    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        log.info("step2.监听创建订单指令");
        log.info("step3.实例化订单聚合根");
        //step3.实例化订单聚合根
        repository.newInstance(
                () -> new OrderAggregate(command));
    }

    //step8.监听提交订单指令
    @CommandHandler
    public void handle(ConfirmOrderCommand command){
        log.info("step8.监听提交订单指令");
        Aggregate<OrderAggregate> aggregate = repository.load(command.getId().toString());
        aggregate.execute(aggregateRoot->aggregateRoot.confirm());
    }

}
