package com.demo.query.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Axon配置类
 * </p>
 *
 * @author wangliang
 * @since 2019/11/19
 */
@Slf4j
@Configuration
public class AmqpConfig {

    @Value("${axon.amqp.exchange}")
    private String exchangeName;

    @Bean
    public Queue orderQueue(){
        return new Queue("order",false);
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(exchangeName).durable(false).build();
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with("#.order.#").noargs();
    }

    @Bean
    public SpringAMQPMessageSource orderQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "order")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.debug("Order message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}
