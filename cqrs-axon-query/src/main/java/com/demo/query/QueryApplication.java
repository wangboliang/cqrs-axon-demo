package com.demo.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.demo.query"})
//@EntityScan(basePackages = {"com.demo.query"})
//@EnableMongoRepositories(basePackages = {"com.demo.query"})
@EnableJpaRepositories(basePackages = "com.demo.query.repository")
@EntityScan(basePackages = {"com.demo.query.entity",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})
public class QueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class, args);
    }
}
