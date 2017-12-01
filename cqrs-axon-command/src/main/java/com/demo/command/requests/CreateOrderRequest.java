package com.demo.command.requests;

import com.demo.api.dto.OrderProduct;
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
