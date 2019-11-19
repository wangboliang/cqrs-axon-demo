package com.demo.query.repository;

import com.demo.query.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wangliang
 * @since 2019/11/19
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntry, String> {

    OrderEntry findById(String id);

    List<OrderEntry> findAll();
}
