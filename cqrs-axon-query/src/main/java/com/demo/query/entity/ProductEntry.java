package com.demo.query.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 商品实体类
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
public class ProductEntry {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private long price;
    @Column
    private int stock;
}
