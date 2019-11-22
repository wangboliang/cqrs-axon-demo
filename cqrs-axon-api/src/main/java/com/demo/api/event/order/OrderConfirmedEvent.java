package com.demo.api.event.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmedEvent {

    @TargetAggregateIdentifier
    private Long id;
}
