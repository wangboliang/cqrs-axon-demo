package com.demo.command.request;

import com.demo.api.model.OrderProduct;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
public class CreateOrderRequest{

  private Long appId;

  private String username;

  private List<OrderProduct> orderProducts;
}
