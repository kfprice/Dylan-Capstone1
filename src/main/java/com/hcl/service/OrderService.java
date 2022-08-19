package com.hcl.service;

import com.hcl.model.Product;
import com.hcl.model.User;
import com.hcl.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.hcl.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    OrderRepository repo;
    private final EmailSender emailSender;

    public List<Order> getAllOrders() {
        return repo.findAll();
    }
    public Optional<Order> getOrderById(int id) {
        return repo.findById(id);
    }
    public void deleteOrder(Integer id) {
        repo.deleteById(id);
    }
    public void addOrder(Order order) {
        repo.save(order);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((User) principal).getEmail();
        String emailString = "Details for your order placed on " + order.getDate() + "\n";
        for(Product product: order.getProducts()) {
            emailString += product.getName() + ": $" + product.getPrice() + "\n";
        }
        emailString += "Total: $" + order.getPrice() + "\n";
        emailString += "Weight: " + order.getWeight() + "Ibs" + "\n";
        emailString += "Shipping " + order.getShippingAddress() + "\n";
        emailString += "Billing " + order.getBillingAddress() + "\n";
        emailSender.sendOrder(email, emailString);

    }

    public List<Order> getUserOrders() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((User) principal);
        List<Order> allOrders = getAllOrders();
        List<Order> returnOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getUser().getUserId() == user.getUserId()) {
                returnOrders.add(order);
            }
        }
        return returnOrders;
    }

    public Optional<Order> getUserOrderById(Integer id) {
        Optional<Order> order = getOrderById(id);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(((User)principal).getUserId() == order.get().getUser().getUserId()) {
            return order;
        }
        return null;
    }
    public void saveOrUpdate(Order order) {
        repo.save(order);
    }
}
