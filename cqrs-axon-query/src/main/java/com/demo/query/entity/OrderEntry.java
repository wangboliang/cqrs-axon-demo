package com.demo.query.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import java.util.Map;

/**
 * <p>
 * 订单实体类
 * </p>
 *
 * @author wangliang
 * @since 2019/11/19
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderEntry {

    @Id
    private String id;
    @Column
    private String username;
    @Column
    private double payment;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @MapKey(name = "id")
    private Map<String, OrderProductEntry> products;
}
