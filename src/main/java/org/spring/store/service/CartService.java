package org.spring.store.service;

import org.spring.store.model.Cart;
import org.spring.store.model.Product;
import org.spring.store.model.User;

public interface CartService {
    Cart findByUser(User user);
    void addProduct(Cart cart, Product product);
    void removeProduct(Cart cart, Product product);
}
