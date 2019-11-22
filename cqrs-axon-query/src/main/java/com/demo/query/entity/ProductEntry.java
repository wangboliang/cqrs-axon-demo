package com.demo.query.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
//@Document
@Entity
public class ProductEntry {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private long price;
    @Column
    private int stock;
}
