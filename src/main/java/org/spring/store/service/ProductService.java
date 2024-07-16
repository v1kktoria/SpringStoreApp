package org.spring.store.service;

import org.spring.store.model.Product;

import java.util.List;

public interface ProductService {
    void create (Product product);
    void update (Product product);
    void delete (Product product);
    List<Product> findAll();
    Product findById(Long id) throws Exception;
    List<Product> findByDescription(String description);
}
