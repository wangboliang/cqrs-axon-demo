package com.demo.command.aggregates;

import com.demo.api.dto.OrderProduct;
import com.demo.api.events.OrderCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * <p>
 * 聚合根AggregateRoot
 * 理论上一个服务一般只会有一个AggregateRoot
 * </p>
 *
 * @author wangliang
 * @since 2017/11/15
 */
@NoArgsConstructor
@ToString
@Slf4j
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private Long orderId;

    /**
     * 主订单编号
     */
    private String mainOrderNo;

    /**
     * 主订单状态
     */
    private Integer mainStatus;

    /**
     * 支付流水号
     */
    private String serialCode;

    /**
     * 支付时间
     */
    private Long payTime;
    /**
     * 用户信息
     */
    private String username;
    /**
     * 待付款金额
     */
    private Long paymentAmount;

    @AggregateMember
    private List<OrderProduct> orderLine;

    private Long appId;

    private String postIp;

    private Long postTime;

    private ScheduleToken closeScheduleToken;

    public OrderAggregate(Long id, String username, List<OrderProduct> orderLine, Long appId,
                          String postIp, String mainOrderNo) {
        apply(new OrderCreatedEvent(id, orderLine, username, appId, postIp, mainOrderNo));
    }


    @EventHandler
    public void on(OrderCreatedEvent event) {
        this.appId = event.getAppId();
        this.orderId = event.getOrderId();
        this.username = event.getUsername();
        this.orderLine = event.getProducts();
        this.postIp = event.getPostIp();
        this.mainOrderNo = event.getMainOrderNo();
    }

}
