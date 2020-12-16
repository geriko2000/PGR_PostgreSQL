package com.PostgreSQLRGR.repos;

import com.PostgreSQLRGR.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findById(Integer id);
    List<Product> findByName(String name);
    List<Product> findByMaterial(String material);
    List<Product> findByType(String type);
    List<Product> findByAvailabilityGreaterThan(Integer availability);
}
