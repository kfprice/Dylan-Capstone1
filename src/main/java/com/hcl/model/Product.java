package com.hcl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;
    String name;
    String description;
    double price;
    double weight;
    int quantity;
    String imageURL;

   /* @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "orderId")) */
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Collection<Order> orders;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private ProductCategory category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return productId;
    }
}
