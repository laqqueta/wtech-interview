package org.laqqueta.widetechinterview.repository;

import org.laqqueta.widetechinterview.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
