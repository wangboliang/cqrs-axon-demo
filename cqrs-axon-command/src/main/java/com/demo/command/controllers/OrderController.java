package com.demo.command.controllers;

import com.demo.api.commands.CreateOrderCommand;
import com.demo.api.dto.BaseResponse;
import com.demo.api.utils.IdWorker;
import com.demo.command.requests.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
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
@RequestMapping("/order")
@Slf4j
public class OrderController {

    /**
     * Axon提供的命令网关，用于发送command命令
     */
    private CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = "/create")
    public BaseResponse create(@RequestBody CreateOrderRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            Long orderId = IdWorker.getId();
            CreateOrderCommand command = new CreateOrderCommand(orderId,
                    request.getAppId(),
                    request.getUsername(),
                    request.getOrderProducts());

            commandGateway.send(command);

            response.setReturnCode(1000);
            response.setMessage("成功");
            response.setDataInfo(orderId);
        } catch (Exception e) {
            log.error("创建订单发生异常:{}", e);
            response.setReturnCode(1004);
            response.setMessage("服务器异常");
        }

        return response;
    }

}
