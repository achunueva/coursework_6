package com.example.coursework_6.controllers;
import com.example.coursework_6.models.Comment;
import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.CommentRepository;
import com.example.coursework_6.repositories.ProductRepository;
import com.example.coursework_6.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CommentController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductService productService;
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
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        existingComment.setName(updatedComment.getName());
        existingComment.setText(updatedComment.getText());
        commentRepository.save(existingComment);
        return  "redirect:/catalog/{id}";
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

}
