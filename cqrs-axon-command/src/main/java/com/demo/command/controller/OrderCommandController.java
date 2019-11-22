package com.demo.command.controller;

import com.demo.api.command.order.CreateOrderCommand;
import com.demo.api.dto.BaseResponse;
import com.demo.api.utils.IdWorker;
import com.demo.command.request.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author wangliang
 * @since 2017/12/1
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderCommandController {

    /**
     * Axon提供的命令网关，用于发送command命令。
     * 每一个Command只会发送到一个CommandHandler去，
     * 当有多个CommandHandler去订阅一个CommandMessage时，
     * 最后一个覆盖前面所有。
     */
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(value = "/create")
    public BaseResponse create(@RequestBody CreateOrderRequest request) {
        BaseResponse response;
        try {
            Long orderId = IdWorker.getId();
            CreateOrderCommand command = new CreateOrderCommand(orderId, request.getUsername(), request.getOrderProducts());
            //step1.发送创建订单指令
            log.info("step1.发送创建订单指令");
            commandGateway.send(command);
            response = BaseResponse.builder().code(1000).message("创建成功").dataInfo(orderId).build();
        } catch (Exception e) {
            log.error("创建订单异常:{}", e);
            response = BaseResponse.builder().code(1001).message("服务异常").dataInfo(null).build();
        }
        return response;
    }
}
