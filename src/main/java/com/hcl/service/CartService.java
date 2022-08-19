package com.hcl.service;

import com.hcl.model.Product;
import com.hcl.repo.CartRepo;
import com.hcl.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CartService implements CartRepo {

    CartRepo repo;
    private final ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        System.out.println(product.getQuantity());
        if (product.getQuantity() > 0) {
            System.out.println("here?");
            if (products.containsKey(product)) {
                if (product.getQuantity() > products.get(product)) {
                    products.replace(product, products.get(product) + 1);
                    System.out.println("Quantity if: " + product.getQuantity());
                }
            } else {
                products.put(product, 1);
                System.out.println("Quantity else" + product.getQuantity());
            }
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1) {
                products.replace(product, products.get(product) - 1);
                product.setQuantity(product.getQuantity() + 1);
            } else if (products.get(product) == 1) {
                products.remove(product);
                product.setQuantity(product.getQuantity() + 1);
            }
        }
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public void checkout() {
        Product prod;
        Iterator<Map.Entry<Product, Integer>> itr = products.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Product, Integer> entry = itr.next();
            prod = productRepository.findById(entry.getKey().getProductId()).get();
            if (prod.getQuantity() < entry.getValue()) {
                entry.getKey().setQuantity(entry.getValue());
            } else {
                entry.getKey().setQuantity(prod.getQuantity() - entry.getValue());
            }
        }
        productRepository.saveAll(products.keySet());
        products.clear();
    }
}