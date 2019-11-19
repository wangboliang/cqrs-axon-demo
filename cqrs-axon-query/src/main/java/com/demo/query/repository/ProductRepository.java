package com.demo.query.repository;

import com.demo.query.entity.ProductEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * </p>
 *
 * @author wangliang
 * @since 2019/11/19
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntry, String> {

    ProductEntry findById(String id);
}
