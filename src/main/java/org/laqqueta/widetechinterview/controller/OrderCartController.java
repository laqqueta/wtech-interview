package org.laqqueta.widetechinterview.controller;

import org.laqqueta.widetechinterview.model.OrderCart;
import org.laqqueta.widetechinterview.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderCartController {

    @Autowired
    private OrderCartService orderCartService;

    @PostMapping("/add")
    public ResponseEntity<OrderCart> addProductToOrderCart(
            @RequestParam Integer productId,
            @RequestParam Integer quantity
    ) {
        OrderCart orderCart = orderCartService.addProductToOrderCart(productId, quantity);
        return new ResponseEntity<>(orderCart, HttpStatus.CREATED);
    }

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder() {
        orderCartService.placeOrder();
        return new ResponseEntity<>("Place Order Success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderCart>> getOrderCarts() {
        List<OrderCart> orderCarts = orderCartService.getAllOrder();
        return new ResponseEntity<>(orderCarts, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPrices() {
        Long totalPrice = orderCartService.getTotalOrderPrice();
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

}
