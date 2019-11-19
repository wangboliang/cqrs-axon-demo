package com.demo.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    private Long jpaId;
    private String id;
    private String name;
    private long price;
    private int amount;
}