package com.nevermind.bu.service.interfaces;

import com.nevermind.bu.entity.Product;

/**
 * Service interface for {@link com.nevermind.bu.entity.Product}.
 *
 * @author Roman Kovaliov
 */
public interface ProductService extends IService<Product> {

    Product getByName(String name);

    Product getByDescription(String description);
}
