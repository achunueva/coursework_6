package com.example.coursework_6.models;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long commentId;
    private String name, text;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getCommentId() {
        return commentId;
    }
    public Comment() {
    }

    public Comment(Long commentId, String name, String text, Product product) {
        this.commentId = commentId;
        this.name = name;
        this.text = text;
        this.product = product;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
