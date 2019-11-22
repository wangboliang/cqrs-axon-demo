package com.demo.query.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * <p>
 * 订单实体类
 * </p>
 *
 * @author wangliang
 * @since 2019/11/19
 */
@Data
@NoArgsConstructor
//@Document
@Entity
public class OrderEntry {
    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private double payment;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderProductEntry> products;
}
