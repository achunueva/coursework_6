package com.example.coursework_6.models;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(length = 255)
    @Lob
    private String description;
    private String color;
    private int price;
    @Column
    private Integer viewCount;


    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Lob
    @Column(length = 16777215)
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(String name, String description, String color, int price) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public Product(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
