package com.demo.command.config;

import com.demo.command.aggregate.OrderAggregate;
import com.demo.command.handler.OrderCommandHandler;
import com.demo.command.saga.OrderSaga;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Axon配置类
 * </p>
 *
 * @author wangliang
 * @since 2017/12/01
 */
@Slf4j
@AllArgsConstructor
@Configuration
public class AxonConfig {

    private AxonConfiguration axonConfiguration;
    private EventBus eventBus;

    @Bean
    public OrderCommandHandler orderCommandHandler() {
        return new OrderCommandHandler(axonConfiguration.repository(OrderAggregate.class), eventBus);
    }

    @Bean
    public SagaConfiguration orderTransferManagementSagaConfiguration() {
        return SagaConfiguration.trackingSagaManager(OrderSaga.class);
    }
}
