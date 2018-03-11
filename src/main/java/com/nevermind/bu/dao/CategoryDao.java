package com.nevermind.bu.dao;

import com.nevermind.bu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for {@link Category}.
 * Reads and writes data in to DB
 *
 * @author Roman Kovaliov
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category findById(int id);

    Category findByName(String name);

    Category findByDescription(String description);

}
