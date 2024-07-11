package org.spring.store.controller;

import lombok.AllArgsConstructor;
import org.spring.store.model.Cart;
import org.spring.store.model.Role;
import org.spring.store.model.User;
import org.spring.store.service.CartService;
import org.spring.store.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private CartService cartService;
    private UserService userService;
    @GetMapping("")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Username " + email + " not found")
        );
        model.addAttribute("username", user.getUsername());
        Cart cart = cartService.findByUser(user);
        model.addAttribute("cart", cart);
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return "adminProfile";
        } else {
            return "userProfile";
        }
    }
}

