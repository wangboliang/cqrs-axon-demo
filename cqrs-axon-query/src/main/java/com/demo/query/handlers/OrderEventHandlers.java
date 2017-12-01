package com.demo.query.handlers;

import com.demo.api.events.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@ProcessingGroup("message")
public class OrderEventHandlers {

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("order {} confirmed", event.getOrderId());
    }

}
