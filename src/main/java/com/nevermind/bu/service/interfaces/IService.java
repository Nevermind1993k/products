package com.nevermind.bu.service.interfaces;

import java.util.List;

/**
 * IService Class
 * Common class for other services that contains all basic operations :
 * Create, Read, Update, Delete
 *
 * @author Roman Kovaliov
 */
public interface IService<T> {

    T getById(int id);

    List<T> getAll();

    void save(T entity);

    void delete(int id);

    void update(T entity);
}
