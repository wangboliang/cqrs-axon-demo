package com.demo.command.saga;

import com.demo.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderSaga {
    private Long identifier;
    private boolean needRollback;

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(OrderCreatedEvent event) {
        log.info("StartSaga...");
        this.identifier = event.getOrderId();
    }

    private void rollBack() {
        if (needRollback) {
            //ToDo
            log.info("roll back :{} ", identifier);
        }
    }

    @SagaEventHandler(associationProperty = "id")
    @EndSaga
    public void handel(OrderCreatedEvent event) {
        log.info("End Saga!");
    }

}
