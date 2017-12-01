package com.demo.api.events;

import com.demo.api.dto.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {

  @TargetAggregateIdentifier
  private Long orderId;

  private List<OrderProduct> products;
  /**
   * 用户id
   */
  private String username;

  private Long appId;

  private String postIp;

  private Long paymentAmount;

  private Integer mainStatus;

  private String mainOrderNo;

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
