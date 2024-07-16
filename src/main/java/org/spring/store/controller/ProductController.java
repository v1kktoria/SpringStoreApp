package org.spring.store.controller;

import lombok.AllArgsConstructor;
import org.spring.store.model.Product;
import org.spring.store.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/products";
    }

    @GetMapping("")
    public String products(Model model, @RequestParam(required = false) String description){
        List<Product> products;
        if (description != null) products = productService.findByDescription(description);
        else products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) throws Exception {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PutMapping("/{id}/edit")
    public String update(@ModelAttribute("product") Product product) {
        productService.update(product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) throws Exception {
        Product product = productService.findById(id);
        productService.delete(product);
        return "redirect:/products";
    }
}
