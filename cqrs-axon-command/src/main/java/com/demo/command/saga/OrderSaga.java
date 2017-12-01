package com.demo.command.saga;

import com.demo.api.dto.OrderProduct;
import com.demo.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Saga
@Slf4j
public class OrderSaga {
    private Long orderIdentifier;
    private ConcurrentMap<Long, OrderProduct> toReserve;
    private ConcurrentMap<Long, OrderProduct> toRollback;
    private int toReserveNumber;
    private boolean needRollback;

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        log.info("StartSaga...");
        this.orderIdentifier = event.getOrderId();
        List<OrderProduct> productList = event.getProducts();
        this.toReserveNumber = productList.size();
        toRollback = new ConcurrentHashMap<>();
        toReserve = new ConcurrentHashMap<>();

        productList.forEach(product -> {
            toReserve.put(product.getId(), product);

            ReserveProductCommand command = new ReserveProductCommand(orderIdentifier,
                    product.getId(),
                    product.getQuantity());

            commandGateway.send(command, new CommandCallback() {

                @Override
                public void onSuccess(CommandMessage commandMessage, Object result) {
                    log.debug("Send ReserveProductCommand successfully.");
                }

                @Override
                public void onFailure(CommandMessage commandMessage, Throwable cause) {
                    if (commandMessage.getPayload() == null) {
                        log.error("Msg payload is null!", cause);
                        return;
                    }
                    ReserveProductCommand cmd = (ReserveProductCommand) commandMessage.getPayload();
                    apply(new ProductReserveFailedEvent(cmd.getOrderId(), cmd.getSkuId()));
                }
            });
        });
    }

    private void tryFinish() {
        if (needRollback) {
            toReserve.forEach((id, product) -> {
                if (!product.isReserved()) {
                    return;
                }
                toRollback.put(id, product);
                commandGateway
                        .send(new RollbackReservationCommand(orderIdentifier, id, product.getQuantity()));
            });
            if (toRollback.isEmpty()) {
                // should send with cancel cause from OrderAutoCancelledEvent
                commandGateway.send(new RollbackOrderCommand(orderIdentifier));
            }
            return;
        }
        commandGateway.send(new ConfirmOrderCommand(orderIdentifier));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReserveFailedEvent event) {
        log.info("Reserve product {} failed", event.getProductId());
        toReserveNumber--;
        needRollback = true;
        if (toReserveNumber == 0) {
            tryFinish();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent event) {
        log.info("product {} reserved for order {}", event.getProductId(), event.getOrderId());
        OrderProduct reservedProduct = toReserve.get(event.getProductId());
        reservedProduct.setReserved(true);
        toReserveNumber--;
        if (toReserveNumber == 0) {
            tryFinish();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductNotEnoughEvent event) {
        log.info("No enough item to buy");
        toReserveNumber--;
        needRollback = true;
        if (toReserveNumber == 0) {
            tryFinish();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ReserveCancelledEvent event) {
        toRollback.remove(event.getProductId());
        if (toRollback.isEmpty()) {
            commandGateway.send(new RollbackOrderCommand(event.getOrderId()));
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderConfirmedEvent event) {
        log.info("Order {} is confirmed", event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handel(OrderAutoCancelledEvent event) {
        log.info("Order {} is auto cancelled", event.getOrderId());
        needRollback = true;
        if (toReserveNumber == 0) {
            tryFinish();
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handel(OrderCancelledEvent event) {
        log.info("Order {} is  cancelled", event.getOrderId());
        log.info("End Saga!");
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handel(OrderDeliveredEvent event) {
        log.info("Order {} is delivered", event.getOrderId());
        log.info("End Saga!");
    }

}
