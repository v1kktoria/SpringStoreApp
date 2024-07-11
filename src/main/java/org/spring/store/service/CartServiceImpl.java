package org.spring.store.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.spring.store.model.Cart;
import org.spring.store.model.Product;
import org.spring.store.model.User;
import org.spring.store.repository.CartRepository;
import org.spring.store.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    UserRepository userRepository;

    @Override
    public Cart findByUser(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    @Override
    @Transactional
    public void addProduct(Cart cart, Product product) {
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeProduct(Cart cart, Product product) {
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }
}
