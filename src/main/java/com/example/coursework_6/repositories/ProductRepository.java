package com.example.coursework_6.repositories;

import com.example.coursework_6.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword%")
    List<Product> searchProducts(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE p.viewCount = (SELECT MAX(p2.viewCount) FROM Product p2)")
    List<Product> findMostViewedProducts();
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
    void incrementViewCount(Long id);
}
