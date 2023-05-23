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
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductService productService;
    @GetMapping("/catalog")
    @Transactional
    public String catalog(Model model) {
        List<Product> mostViewedProducts = productRepository.findMostViewedProducts();
        model.addAttribute("mostViewedProducts", mostViewedProducts);
        List<Product> product = productRepository.findAll();
        model.addAttribute("product", product);
        return "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductPage(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        Product producten = productRepository.findById(id).orElse(null);
        if (producten != null) {
            producten.setViewCount(producten.getViewCount() + 1);
            productRepository.save(producten);
        }
        if (product.isPresent()) {
            Product product1 = product.get();
            List<Comment> comment = commentRepository.findByProductId(id);
            model.addAttribute("product", product1);
            model.addAttribute("comment", comment);
            ArrayList<Product> res = new ArrayList<>();
            product.ifPresent(res::add);
            model.addAttribute("product", res);
            return "product";
        } else {
            return "redirect:/product";
        }
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return "redirect:/admin/ud";
    }
    @GetMapping("/product/update/{id}")
    public String productEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return "redirect:/catalog/{id}";
        }
        model.addAttribute("product", product.get());
        return "update_product";
    }
}
