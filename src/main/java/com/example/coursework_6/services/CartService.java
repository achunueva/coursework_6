package com.example.coursework_6.services;

import com.example.coursework_6.models.Cart;
import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.CartRepository;
import com.example.coursework_6.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    public void saveCartToDB(MultipartFile file1, String cartName, String cartDescription, String cartColor, int cartPrice){
        Cart cart = new Cart(cartName, cartDescription, cartColor, cartPrice);
        String fileName = StringUtils.cleanPath(file1.getOriginalFilename());

        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            cart.setCartImage(Base64.getEncoder().encodeToString(file1.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cart.setCartColor(cartColor);
        cart.setCartDescription(cartDescription);
        cart.setCartName(cartName);
        cart.setCartPrice(cartPrice);
        cartRepository.save(cart);
    }
}
