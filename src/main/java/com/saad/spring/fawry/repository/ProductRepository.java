package com.saad.spring.fawry.repository;

import com.saad.spring.fawry.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.active = ?1")
    List<Product> findByActive(Boolean active, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = ?1")
    List<Product> findByActive(Boolean active);

}
