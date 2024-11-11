package org.laqqueta.widetechinterview.repository;

import org.laqqueta.widetechinterview.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCartRepository extends JpaRepository<OrderCart, Integer> {
}
