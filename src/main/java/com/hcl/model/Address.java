package com.hcl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int addressId;
    String city;
    String state;
    String zipcode;
    String country;
    String apt;
}