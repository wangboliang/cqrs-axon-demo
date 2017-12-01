package com.demo.api.commands;

import com.demo.api.dto.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand implements Serializable {

  @TargetAggregateIdentifier
  private Long orderId;
  private Long appId;
  private String username;
  private List<OrderProduct> orderProducts;
}
