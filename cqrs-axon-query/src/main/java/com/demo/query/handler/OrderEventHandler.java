package com.demo.query.handler;

import com.demo.api.event.OrderCreatedEvent;
import com.demo.query.entity.OrderEntry;
import com.demo.query.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventHandler {

    @Autowired
    private OrderRepository repository;

    //step6.处理创建订单事件
    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("处理创建订单事件: {}", event);
        OrderEntry orderEntry = new OrderEntry();
        BeanUtils.copyProperties(event,orderEntry);
        repository.save(orderEntry);
    }

}
