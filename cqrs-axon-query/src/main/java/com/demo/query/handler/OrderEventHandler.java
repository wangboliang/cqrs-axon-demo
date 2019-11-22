package com.demo.query.handler;

import com.demo.api.event.order.OrderCreatedEvent;
import com.demo.query.entity.OrderEntry;
import com.demo.query.entity.OrderProductEntry;
import com.demo.query.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@ProcessingGroup("order")
public class OrderEventHandler {

    @Autowired
    private OrderRepository repository;

    //step5-1.异步监听创建订单事件
    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("step5-1.异步监听创建订单事件，更新mysql数据");
        List<OrderProductEntry> products = new ArrayList<>();
        event.getProducts().forEach((product) -> {
            products.add(
                    new OrderProductEntry(product.getProductId(),
                            product.getName(),
                            product.getPrice(),
                            product.getAmount()));
        });
        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setId(event.getOrderId());
        orderEntry.setUsername(event.getUsername());
        orderEntry.setProducts(products);
        repository.save(orderEntry);
    }

}
