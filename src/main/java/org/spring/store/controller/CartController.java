package org.spring.store.controller;

import lombok.AllArgsConstructor;
import org.spring.store.model.Cart;
import org.spring.store.model.Product;
import org.spring.store.model.User;
import org.spring.store.service.CartService;
import org.spring.store.service.ProductService;
import org.spring.store.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private ProductService productService;
    private UserService userService;

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email " + email)
        );
        Product product = productService.findById(productId);
        if (product != null) {
            Cart cart = cartService.findByUser(user);
            cartService.addProduct(cart, product);
        }
        return "redirect:/products";
    }

    @DeleteMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email " + email)
        );
        Product product = productService.findById(productId);
        if (product != null) {
            Cart cart = cartService.findByUser(user);
            cartService.removeProduct(cart, product);
        }
        return "redirect:/profile";
    }
}
