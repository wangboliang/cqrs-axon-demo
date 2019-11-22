package com.demo.command.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Amqp配置类
 *
 * </p>
 *
 * @author wangliang
 * @since 2017/12/01
 */
@Slf4j
@Configuration
public class AmqpConfig {

    @Value("${axon.amqp.exchange}")
    private String exchangeName;

    @Bean
    public Queue orderQueue() {
        return new Queue("order", false);
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(false).build();
    }

    /**
     * 使用topicExchange模式时，这里routingKey会匹配event类路径。
     * 不匹配会导致event publish不到queue
     * “*”可以代替一个词。
     * “#”可以代替0个或者多个单词。
     *
     * @return
     */
    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with("#.order.#").noargs();
    }

//    @Bean
//    public SpringAMQPMessageSource orderQueueMessageSource(Serializer serializer){
//        return new SpringAMQPMessageSource(serializer){
//            @RabbitListener(queues = "order")
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                log.debug("Order message received: "+message.toString());
//                super.onMessage(message, channel);
//            }
//        };
//    }
}
