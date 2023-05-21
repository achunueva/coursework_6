package com.example.coursework_6.controllers;


import com.example.coursework_6.models.Comment;
import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.CommentRepository;
import com.example.coursework_6.repositories.ProductRepository;
import com.example.coursework_6.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.aot.generate.GeneratedTypeReference;
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

    @PostMapping("/product/{id}/comment")
    public String createComment(@PathVariable("id") Long id, @ModelAttribute("newComment") Comment comment) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            comment.setProduct(product);
            commentRepository.save(comment);
        }
        return "redirect:/catalog/{id}";
    }

    @GetMapping("/product/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId) {
        commentRepository.deleteById(commentId);
        return "redirect:/catalog/{id}";
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
    @PostMapping("/product/update/{id}")
    public String commentUpdate(@PathVariable(value="id") long id, @ModelAttribute("product") Product updated){
        Product existing = productRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setColor(updated.getColor());
        existing.setPrice(updated.getPrice());
        productRepository.save(existing);
        return "redirect:/admin/ud";
    }

    @GetMapping("/product/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable("id") long id, @PathVariable("commentId") long commentId, Product product,
                                Model model) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + commentId));

        model.addAttribute("comment", comment);
        return  "comment_update";
    }
    @PostMapping("/product/{id}/comment/{commentId}/update")
    public String updateComment(@PathVariable("id") long id, @PathVariable("commentId") long commentId,
                                @ModelAttribute("comment") Comment updatedComment) {
        // Retrieve the existing comment from the database
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        // Update the properties of the existing comment with the new values
        existingComment.setName(updatedComment.getName());
        existingComment.setText(updatedComment.getText());

        // Save the updated comment back to the database
        commentRepository.save(existingComment);

        // Redirect the user back to the product page or display a success message
        return  "redirect:/catalog/{id}";
    }

}
