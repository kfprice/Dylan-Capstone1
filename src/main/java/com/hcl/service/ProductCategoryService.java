package com.hcl.service;
import com.hcl.model.ProductCategory;
import com.hcl.repo.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository repo;
    public List<ProductCategory> getAllProductCategories() {
        return repo.findAll();
    }
    public Optional<ProductCategory> getProductCategoryById(int id) {
        return repo.findById(id);
    }
    public void deleteProductCategory(Integer id) {
        repo.deleteById(id);
    }
    public void addProductCategory(ProductCategory productCategory) {
        repo.save(productCategory);
    }
    public void saveOrUpdate(ProductCategory productCategory) {
        repo.save(productCategory);
    }
}
