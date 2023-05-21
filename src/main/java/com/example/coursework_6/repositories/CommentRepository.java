package com.example.coursework_6.repositories;

import com.example.coursework_6.models.Comment;
import com.example.coursework_6.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProduct(Product product);

    List<Comment> findByProductId(Long id);
}