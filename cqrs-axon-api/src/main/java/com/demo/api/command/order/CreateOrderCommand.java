package com.demo.api.command.order;

import com.demo.api.model.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private Long orderId;
    private String username;
    private List<OrderProduct> products;
}
