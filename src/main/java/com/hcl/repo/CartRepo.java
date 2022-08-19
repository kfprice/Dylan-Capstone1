package com.hcl.repo;
import com.hcl.model.Product;
import java.util.Map;

public interface CartRepo {
    void addProduct(Product product);
    void removeProduct(Product product);
    Map<Product, Integer> getProducts();
    void checkout();
}
