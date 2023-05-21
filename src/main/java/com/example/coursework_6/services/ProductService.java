package com.example.coursework_6.services;


import com.example.coursework_6.models.Product;
import com.example.coursework_6.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public void saveProductToDB(MultipartFile file, String name, String description, String color, int price){
        Product product = new Product(name, description, color, price);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setPrice(price);
        product.setDescription(description);
        product.setColor(color);
        product.setName(name);
        productRepository.save(product);
    }



}
