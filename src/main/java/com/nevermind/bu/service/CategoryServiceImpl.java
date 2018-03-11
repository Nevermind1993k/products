package com.nevermind.bu.service;

import com.nevermind.bu.dao.CategoryDao;
import com.nevermind.bu.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nevermind.bu.service.interfaces.CategoryService;

import java.util.List;

/**
 * Implementation of {@link CategoryService} interface.
 *
 * @author Roman Kovaliov
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category getByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public Category getByDescription(String description) {
        return categoryDao.findByDescription(description);
    }

    @Override
    public Category getById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryDao.save(category);
    }
}
