package com.demo.command.handler;

import com.demo.api.command.CreateOrderCommand;
import com.demo.command.aggregate.OrderAggregate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
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
@Component
@Slf4j
@AllArgsConstructor
public class OrderCommondHandler {

    private Repository<OrderAggregate> repository;

//  private QuartzEventScheduler quartzEventScheduler;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        repository.newInstance(
                () -> new OrderAggregate(command.getOrderId(), command.getUsername(),
                        command.getOrderProducts(), command.getAppId(), "",
                        ""));
    }

}
