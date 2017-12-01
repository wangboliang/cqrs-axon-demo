package com.demo.query.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
@Configuration
@Slf4j
public class AxonConfiguration {
    // listen to queue
    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {
            @RabbitListener(queues = "moses-simpleorder-query")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("message received: " + message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}
