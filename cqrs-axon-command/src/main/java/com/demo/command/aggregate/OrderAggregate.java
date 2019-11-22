package com.demo.command.aggregate;

import com.demo.api.event.order.OrderConfirmedEvent;
import com.demo.api.event.order.OrderCreatedEvent;
import com.demo.api.model.OrderProduct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * <p>
 * 聚合根
 * </p>
 *
 * @author wangliang
 * @since 2017/11/15
 */
@Slf4j
@Getter
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private Long id;
    private String username;
    private double payment;

    @AggregateMember
    private List<OrderProduct> products;

    public OrderAggregate() {
    }

    public OrderAggregate(Long orderId, String username, List<OrderProduct> products) {
        //step4.发起创建订单事件
        log.info("step4.发起创建订单事件");
        apply(new OrderCreatedEvent(orderId, username, products));
    }

    //step5.同步监听创建订单事件，更新Aggregate状态
    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("step5.同步监听创建订单事件，更新Aggregate状态");
        this.id = event.getOrderId();
        this.username = event.getUsername();
        this.products = event.getProducts();
        computePrice();
    }

    private void computePrice() {
        products.forEach((product) -> {
            payment += product.getPrice() * product.getAmount();
        });
    }

    //step9.发起提交订单事件
    public void confirm() {
        log.info("step9.发起提交订单事件");
        apply(new OrderConfirmedEvent(id));
    }
}
