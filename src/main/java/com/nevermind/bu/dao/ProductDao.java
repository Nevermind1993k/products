package com.nevermind.bu.dao;

import com.nevermind.bu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for {@link Product}.
 * Reads and writes data in to DB
 *
 * @author Roman Kovaliov
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findById(int id);

    Product findByName(String name);

    Product findByDescription(String description);

}
