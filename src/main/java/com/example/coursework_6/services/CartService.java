package com.example.coursework_6.services;

import com.example.coursework_6.models.Cart;
import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.CartRepository;
import com.example.coursework_6.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    public Cart addToCart(Long id){
        Product product = productRepository.findById(id).get();
        if(product != null){
            Cart cart  = new Cart(product);
            return cartRepository.save(cart);
        }
        return null;
    }
}
