package com.example.coursework_6.controllers;
import com.example.coursework_6.models.Cart;
import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.CartRepository;
import com.example.coursework_6.repositories.CommentRepository;
import com.example.coursework_6.repositories.ProductRepository;
import com.example.coursework_6.services.CartService;
import com.example.coursework_6.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CartController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/cart")
    public String cart(Model model){
        Iterable<Cart> cart = cartRepository.findAll();
        model.addAttribute("cart", cart);
        return "cart";
    }
    @PostMapping("/product/{id}/cart")
    public String createCart(@PathVariable("id") Long id, @ModelAttribute("newComment") Cart cart) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            cart.setProduct(product);
            cartRepository.save(cart);
        }
        return "redirect:/catalog/{id}";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable("id") Integer cartId){
        cartRepository.deleteById(cartId);
        return "redirect:/cart";
    }


    //    @PostMapping("/product/{id}/cart")
//    public String addCart(@RequestParam String cartName,
//                          @RequestParam("file1") MultipartFile file1,
//                          @RequestParam String cartDescription,
//                          @RequestParam String cartColor,
//                          @RequestParam int cartPrice) throws IOException{
//        File file = new File(String.valueOf(file1));
//        cartService.saveCartToDB(file1, cartName, cartDescription, cartColor, cartPrice);
//        return ("redirect:/cart");
//    }


}
