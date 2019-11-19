package com.demo.query.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEntry {
    @Id
    @GeneratedValue
    private Long jpaId;
    private String id;
    @Column
    private String name;
    @Column
    private long price;
    @Column
    private int amount;
}
