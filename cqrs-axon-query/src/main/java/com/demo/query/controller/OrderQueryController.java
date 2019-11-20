package com.demo.query.controller;

import com.demo.api.dto.BaseResponse;
import com.demo.query.entity.OrderEntry;
import com.demo.query.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
public class OrderQueryController {

    private OrderRepository repository;

    public OrderQueryController(OrderRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResponse list() {
        BaseResponse response;
        try {
            List<OrderEntry> orderEntryList = repository.findAll();
            response = BaseResponse.builder().code(1000).message("查询成功").dataInfo(orderEntryList).build();
        } catch (Exception e) {
            log.error("订单列表查询异常:{}", e);
            response = BaseResponse.builder().code(1001).message("服务异常").dataInfo(null).build();
        }
        return response;
    }

    @GetMapping(value = "/query/{id}")
    public BaseResponse query(@PathVariable("id") String orderId) {
        BaseResponse response;
        try {
            OrderEntry orderEntry = repository.findById(orderId);
            response = BaseResponse.builder().code(1000).message("查询成功").dataInfo(orderEntry).build();
        } catch (Exception e) {
            log.error("订单查询异常:{}", e);
            response = BaseResponse.builder().code(1001).message("服务异常").dataInfo(null).build();
        }
        return response;
    }
}
