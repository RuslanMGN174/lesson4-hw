package ru.knyazev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.knyazev.entites.Product;
import ru.knyazev.entites.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }

        if (product.getId() != null) {
            productRepository.update(product);
        } else {
            productRepository.insert(product);
        }
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        productRepository.delete(id);
        return "redirect:/product";
    }
}