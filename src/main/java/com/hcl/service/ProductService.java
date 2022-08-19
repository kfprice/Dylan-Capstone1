package com.hcl.service;
import com.hcl.model.Product;
import com.hcl.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;
    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    public Optional<Product> getProductById(int id) {
        return repo.findById(id);
    }
    public void deleteProduct(Integer id) {
        repo.deleteById(id);
    }
    public void addProduct(Product product) {
        repo.save(product);
    }
    public void saveOrUpdate(Product product) {
        repo.save(product);
    }
}
