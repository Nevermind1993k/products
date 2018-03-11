package com.nevermind.bu.service.interfaces;

import com.nevermind.bu.entity.Category;

/**
 * Service interface for {@link Category}.
 *
 * @author Roman Kovaliov
 */
public interface CategoryService extends IService<Category> {

    Category getByName(String name);

    Category getByDescription(String description);
}
