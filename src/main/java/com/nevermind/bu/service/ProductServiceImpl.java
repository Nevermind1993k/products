package com.nevermind.bu.service;

import com.nevermind.bu.dao.ProductDao;
import com.nevermind.bu.entity.Product;
import com.nevermind.bu.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link ProductService} interface.
 *
 * @author Roman Kovaliov
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public Product getByDescription(String description) {
        return productDao.findByDescription(description);
    }

    @Override
    public Product getById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }
}
