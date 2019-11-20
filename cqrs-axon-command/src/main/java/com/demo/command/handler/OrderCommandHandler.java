package com.demo.command.handler;

import com.demo.api.command.CreateOrderCommand;
import com.demo.command.aggregate.OrderAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
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

    //step2.处理创建订单指令
    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        //step3.实例化订单聚合根
        repository.newInstance(
                () -> new OrderAggregate(command));
    }

}
