package com.demo.query.handler;

import com.demo.api.event.OrderCreatedEvent;
import com.demo.query.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventHandler {

    private OrderRepository repository;

    @Autowired
    public OrderEventHandler(OrderRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("order {} confirmed", event.getOrderId());
    }

}
