package com.demo.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <p>
 * 商品订单实体
 * </p>
 *
 * @author wangliang
 * @since 2019/11/19
 */
@Data
@NoArgsConstructor
@Entity
public class OrderProductEntry {
    @Id
    @GeneratedValue
    private Long jpaId;
    @Column
    private Long productId;
    @Column
    private String name;
    @Column
    private long price;
    @Column
    private int amount;

    public OrderProductEntry(Long productId, String name, long price, int amount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
