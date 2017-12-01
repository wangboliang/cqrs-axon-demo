package com.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderProduct {

    private Long id;
    private String name;
    private long price;
    private int quantity;
    private long amount;
    private boolean reserved;
}