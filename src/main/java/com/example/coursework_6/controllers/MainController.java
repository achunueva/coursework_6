package com.example.coursework_6.controllers;


import com.example.coursework_6.models.Comment;
import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.CommentRepository;
import com.example.coursework_6.repositories.ProductRepository;
import com.example.coursework_6.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.processor.comment.ICommentStructureHandler;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @Transactional
    public String main(Model model){
        List<Product> mostViewedProducts = productRepository.findMostViewedProducts();
        model.addAttribute("mostViewedProducts", mostViewedProducts);
        return "main";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }
    @PostMapping("/admin")
    public String addProduct(@RequestParam String name,
                          @RequestParam MultipartFile file,
                          @RequestParam String description,
                          @RequestParam String color,
                          @RequestParam int price){
        productService.saveProductToDB(file, name, description, color, price);
        return ("redirect:/catalog");
    }
    @GetMapping("/admin/ud")
    public String deleteProduct(Model model){
        List<Product> product = productRepository.findAll();
        model.addAttribute("product", product);
        return "admin_delete";
    }

    @GetMapping("/catalog/admin/{id}")
    public String foodDetails(@PathVariable(value = "id") Long id, Model model){
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);
        return "product_admin";
    }


    @GetMapping("/search")
    @Transactional
    public String search(@RequestParam("query") String query, Model model) {
        List<Product> mostViewedProducts = productRepository.findMostViewedProducts();
        model.addAttribute("mostViewedProducts", mostViewedProducts);
        List<Product> product = productRepository.searchProducts(query);
        model.addAttribute("product", product);
        return "catalog";
    }
}
