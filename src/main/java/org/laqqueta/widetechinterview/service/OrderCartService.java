package org.laqqueta.widetechinterview.service;

import org.laqqueta.widetechinterview.model.OrderCart;
import org.laqqueta.widetechinterview.model.Product;
import org.laqqueta.widetechinterview.repository.OrderCartRepository;
import org.laqqueta.widetechinterview.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCartService {
    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderCart addProductToOrderCart(Integer productId, Integer quantity) {
        Product productToAdd = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product doesn't exists"));

        if (productToAdd.getQuantity() < quantity)
            throw new RuntimeException("Not enough stock for this product.");

        OrderCart orderCart = new OrderCart();
        orderCart.setProduct(productToAdd);
        orderCart.setQuantity(quantity);
        orderCart.setTotalPrice(productToAdd.getPrice() * quantity);
        orderCartRepository.save(orderCart);

        productToAdd.setQuantity(productToAdd.getQuantity() - quantity);
        productRepository.save(productToAdd);

        return orderCartRepository.save(orderCart);
    }

    public List<OrderCart> getAllOrder() {
        return orderCartRepository.findAll();
    }

    public Long getTotalOrderPrice() {
        return orderCartRepository.findAll().stream()
                .mapToLong(OrderCart::getTotalPrice)
                .sum();
    }

    public void placeOrder() {
        // TODO: ????
        if (orderCartRepository.findAll().isEmpty()) throw new RuntimeException("Empty Order");

        orderCartRepository.deleteAll();
    }
}
