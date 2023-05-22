package com.example.coursework_6.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    private String cartName;
    @Column(length = 255)
    @Lob
    private String cartDescription;
    private String cartColor;
    private int cartPrice;

    @Lob
    @Column(length = 16777215)
    private String cartImage;

    public String getCartImage() {
        return cartImage;
    }

    public void setCartImage(String image) {
        this.cartImage = cartImage;
    }


    public Cart() {
    }

    public Cart(String cartName, String cartDescription, String cartColor, int cartPrice) {
        this.cartId = cartId;
        this.cartName = cartName;
        this.cartDescription = cartDescription;
        this.cartColor = cartColor;
        this.cartPrice = cartPrice;
        this.product = product;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartDescription() {
        return cartDescription;
    }

    public void setCartDescription(String cartDescription) {
        this.cartDescription = cartDescription;
    }

    public String getCartColor() {
        return cartColor;
    }

    public void setCartColor(String cartColor) {
        this.cartColor = cartColor;
    }

    public int getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(int cartPrice) {
        this.cartPrice = cartPrice;
    }

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Cart(Product product) {
        this.product = product;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
