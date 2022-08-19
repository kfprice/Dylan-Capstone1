package com.hcl.controller;

import com.hcl.model.Product;
import com.hcl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public Optional<Product> getProductByID(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/product/{id}")
    public void updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        Product updateProduct = productService.getProductById(id).get();
        if(updateProduct != null) {
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setWeight(product.getWeight());
            updateProduct.setQuantity(product.getQuantity());
            updateProduct.setImageURL(product.getImageURL());
            updateProduct.setOrders(product.getOrders());
            updateProduct.setCategory(product.getCategory());
            productService.saveOrUpdate(updateProduct);
        }
    }
}