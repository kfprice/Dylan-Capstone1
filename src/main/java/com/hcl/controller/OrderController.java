package com.hcl.controller;

import com.hcl.model.Order;
import com.hcl.model.Product;
import com.hcl.model.User;
import com.hcl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public List<Order> getOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public Optional<Order> getOrderID(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/order")
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/order")
    public void updateOrder(@RequestBody Order order) {
        orderService.saveOrUpdate(order);
    }

    @GetMapping("/user/order")
    public List<Order> getUserOrders() {
        return orderService.getUserOrders();
    }

    @GetMapping("/user/order/{id}")
    public Optional<Order> getUserOrderById(@PathVariable Integer id) {
        return orderService.getUserOrderById(id);
    }
}