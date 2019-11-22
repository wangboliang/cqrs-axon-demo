package com.demo.command.saga;

import com.demo.api.command.order.ConfirmOrderCommand;
import com.demo.api.event.order.OrderConfirmedEvent;
import com.demo.api.event.order.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Saga
public class OrderSaga {

    private Long orderIdentifier;
    private boolean needRollback;

    @Autowired
    private transient CommandGateway commandGateway;

    //step6.监听创建订单事件，启动创建订单事务
    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        log.info("step6.监听创建订单事件，启动创建订单事务");
        log.info("StartSaga...");
        this.orderIdentifier = event.getOrderId();
        tryFinish();
    }

    private void tryFinish() {
        if(needRollback){
            return;
        }
        //step7.发起提交订单指令
        log.info("step7.发起提交订单指令");
        commandGateway.send(new ConfirmOrderCommand(orderIdentifier));
    }

    //step10.监听提交订单事件，结束创建订单事件事务
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    @EndSaga
    public void handel(OrderConfirmedEvent event) {
        log.info("step10.监听提交订单事件，结束创建订单事件事务");
        log.info("End Saga!");
    }

}
