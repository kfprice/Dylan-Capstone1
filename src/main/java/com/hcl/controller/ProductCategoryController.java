package com.hcl.controller;

import com.hcl.model.ProductCategory;
import com.hcl.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/productCategory")
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getAllProductCategories();
    }

    @GetMapping("/productCategory/{id}")
    public Optional<ProductCategory> getProductCategoryById(@PathVariable Integer id) {
        return productCategoryService.getProductCategoryById(id);
    }

    @PostMapping("/productCategory")
    public void addProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.addProductCategory(productCategory);
    }

    @DeleteMapping("/productCategory/{id}")
    public void deleteProductCategory(@PathVariable Integer id) {
        productCategoryService.deleteProductCategory(id);
    }

    @PutMapping("/productCategory")
    public void updateProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.saveOrUpdate(productCategory);
    }
}