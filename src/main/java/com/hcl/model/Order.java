package com.hcl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int orderId;
    double price;
    LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "orderId"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "productId"))
    private Collection<Product> products;


    boolean status;
    String billingAddress;
    String shippingAddress;
    double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;

    public void setDate() {
        this.date = LocalDate.now();
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

}