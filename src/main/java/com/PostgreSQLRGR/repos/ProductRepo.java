package com.PostgreSQLRGR.repos;

import com.PostgreSQLRGR.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByMaterial(String material);
    List<Product> findByType(String type);
    List<Product> findByAvailabilityGreaterThan(Integer availability);
    List<Product> findByNameAndAvailabilityGreaterThan(String name, Integer availability);
    List<Product> findByMaterialAndAvailabilityGreaterThan(String material, Integer availability);
    List<Product> findByTypeAndAvailabilityGreaterThan(String type, Integer availability);
    List<Product> findByCostGreaterThanEqual(Integer cost);
    List<Product> findByCostLessThanEqual(Integer cost);
    List<Product> findByCostLessThanEqualAndCostGreaterThanEqual(Integer maxCost, Integer minCost);
}
