package org.spring.store.controller;

import lombok.AllArgsConstructor;
import org.spring.store.model.Role;
import org.spring.store.model.User;
import org.spring.store.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping("")
    private String findAll(Model model, @RequestParam(required = false) String email) {
        List<User> users;
        if (email != null) users = userService.findByEmailContaining(email);
        else users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PutMapping("/ban/{userId}")
    private String banUser(@PathVariable Long userId)
    {
        userService.banUser(userId);
        return "redirect:/users";
    }

    @PutMapping("/unban/{userId}")
    private String unbanUser(@PathVariable Long userId)
    {
        userService.unbanUser(userId);
        return "redirect:/users";
    }

    @PutMapping("/role/{userId}")
    private String updateUserRole(@PathVariable Long userId, @RequestParam Role role)
    {
        userService.updateUserRole(userId, role);
        return "redirect:/users";
    }
}
