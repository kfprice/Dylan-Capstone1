package com.hcl.controller;


import com.hcl.model.Product;
import com.hcl.model.Order;
import com.hcl.model.User;
import com.hcl.service.CartService;
import com.hcl.service.OrderService;
import com.hcl.service.ProductService;
import com.hcl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CartController {
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/cart")
    public String getCart() {
        String str = "";
        for(Map.Entry<Product, Integer> entry : cartService.getProducts().entrySet()) {
            str += entry.getKey().getName() + " x " + entry.getValue() + ": $"
                    + entry.getKey().getPrice() * entry.getValue() + "\n";
        }
        str += "Total: $" + getCartTotal();
        return str;
    }

    @PostMapping("/cart/addItem/{productId}")
    public void addToCart(@PathVariable("productId") Integer productId) {
        cartService.addProduct(productService.getProductById(productId).get());
    }

    @DeleteMapping("/cart/removeItem/{productId}")
    public void removeFromCart(@PathVariable("productId") Integer productId) {
        cartService.removeProduct(productService.getProductById(productId).get());
    }

    @GetMapping("/cart/checkout")
    public void checkout() {
        if (cartService.getProducts().size() > 0) {
            Order newOrder = new Order();
            newOrder.setDate();
            newOrder.setPrice(getCartTotal());
            newOrder.setWeight(getCartWeight());
            Collection<Product> products = new ArrayList<>();
            for (Map.Entry<Product, Integer> entry : cartService.getProducts().entrySet()) {
                System.out.println("first if");
                for (int i = 0; i < entry.getValue(); i++) {
                    System.out.println("second if");
                    products.add(entry.getKey());
                }
            }
            newOrder.setProducts(products);
            System.out.println(newOrder.getProducts().toString());
            newOrder.setStatus(false);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = ((User) principal);
            String address = userService.getUserById(user.getUserId()).get().getUserAddresses().get(0).toString();
            newOrder.setShippingAddress(address);
            newOrder.setBillingAddress(address);
            newOrder.setUser(user);
            orderService.addOrder(newOrder);
            cartService.checkout();
        }
    }

    public double getCartTotal() {
        double totalPrice = 0;
        for(Map.Entry<Product, Integer> entry : cartService.getProducts().entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public double getCartWeight() {
        double totalWeight = 0;
        for(Map.Entry<Product, Integer> entry : cartService.getProducts().entrySet()) {
            totalWeight += entry.getKey().getWeight() * entry.getValue();
        }
        return totalWeight;
    }
}
