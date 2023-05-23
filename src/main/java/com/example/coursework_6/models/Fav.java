package com.example.coursework_6.models;


import jakarta.persistence.*;

@Entity
@Table(name = "fav")
public class Fav {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer favId;

    private String favName;
    private String favPrice;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    private Cart cart;

//    public Cart getCart() {
//        return cart;
//    }

//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orders;

    public Fav() {
    }

    public Integer getFavId() {
        return favId;
    }

    public void setFavId(Integer favId) {
        this.favId = favId;
    }

    public String getFavName() {
        return favName;
    }

    public void setFavName(String favName) {
        this.favName = favName;
    }

    public String getFavPrice() {
        return favPrice;
    }

    public void setFavPrice(String favPrice) {
        this.favPrice = favPrice;
    }

    public Order getOrder() {
        return orders;
    }

    public void setOrder(Order orders) {
        this.orders = orders;
    }

    public Fav(Integer favId, String favName, String favPrice,int quantity, Order orders) {
        this.favId = favId;
        this.favName = favName;
        this.favPrice = favPrice;
        this.quantity = quantity;
        this.orders = orders;
    }
}
