package org.spring.store.service;

import lombok.AllArgsConstructor;
import org.spring.store.model.Product;
import org.spring.store.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Product findById(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(
                () -> new Exception("Not found product with id " + id)
        );
    }

    @Override
    public List<Product> findByDescription(String description) {
        return productRepository.findByDescriptionContaining(description);
    }
}
