package com.example.coursework_6.controllers;


import com.example.coursework_6.models.Cart;
import com.example.coursework_6.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/addToCart/{id}")
    public Cart addToCart(@PathVariable(name = "id") Long id){
        return cartService.addToCart(id);
    }
}
