package com.example.coursework_6.services;
import com.example.coursework_6.models.Cart;
import com.example.coursework_6.repositories.CartRepository;
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
    public void saveCartToDB(String cartName, String cartDescription, String cartColor, int cartPrice){
        Cart cart = new Cart(cartName, cartDescription, cartColor, cartPrice);

        cart.setCartColor(cartColor);
        cart.setCartDescription(cartDescription);
        cart.setCartName(cartName);
        cart.setCartPrice(cartPrice);
        cartRepository.save(cart);
    }
}
