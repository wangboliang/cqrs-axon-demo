package com.demo.api.event;

import com.demo.api.model.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private Long orderId;

    private String username;

    private List<OrderProduct> products;

    private Long appId;

    private String postIp;

    private Long paymentAmount;

    private Integer mainStatus;

    private String mainOrderNo;

    public OrderCreatedEvent(Long orderId, String username,
                             List<OrderProduct> products) {
        this.orderId = orderId;
        this.username = username;
        this.products = products;
    }

    public OrderCreatedEvent(Long orderId,
                             List<OrderProduct> products, String username, Long appId, String postIp,
                             String mainOrderNo) {
        this.orderId = orderId;
        this.products = products;
        this.username = username;
        this.appId = appId;
        this.postIp = postIp;
        this.mainOrderNo = mainOrderNo;
    }
}
