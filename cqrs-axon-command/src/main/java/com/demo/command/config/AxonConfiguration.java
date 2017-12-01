package com.demo.command.config;

import com.demo.command.saga.OrderSaga;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <p>
 * Axon配置类
 * </p>
 *
 * @author wangliang
 * @since 2017/12/01
 */
@Configuration
@Slf4j
public class AxonConfiguration {
    // Saga Configuration
    @Autowired
    private PlatformTransactionManager transactionManager;

    // listen to queue
    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = "moses-simpleorder-command") // Saga depends on it.
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("message received: {}", message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    public SagaConfiguration<OrderSaga> orderSagaConfiguration(Serializer serializer) {
        SagaConfiguration<OrderSaga> sagaConfiguration = SagaConfiguration.subscribingSagaManager(
                OrderSaga.class,
                c -> springAMQPMessageSource(serializer));
        sagaConfiguration.registerHandlerInterceptor(c -> transactionManagingInterceptor());
        return sagaConfiguration;
    }

    @Bean
    public TransactionManagingInterceptor transactionManagingInterceptor() {
        return new TransactionManagingInterceptor(new SpringTransactionManager(transactionManager));
    }
}
