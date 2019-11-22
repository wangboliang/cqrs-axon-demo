package com.demo.api.event.order;

import com.demo.api.model.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    @TargetAggregateIdentifier
    private Long orderId;
    private String username;
    @AggregateMember
    private List<OrderProduct> products;
}
