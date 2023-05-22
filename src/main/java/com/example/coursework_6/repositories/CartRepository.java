package com.example.coursework_6.repositories;


import com.example.coursework_6.models.Cart;
import com.example.coursework_6.models.Comment;
import com.example.coursework_6.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    List<Cart> findByProductId(Long id);
}
