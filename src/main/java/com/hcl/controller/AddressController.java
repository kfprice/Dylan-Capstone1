package com.hcl.controller;

import com.hcl.model.Address;
import com.hcl.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public List<Address> getAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/address/{id}")
    public Optional<Address> getAddressID(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/address")
    public void addAddress(@RequestBody Address address) {
        addressService.addAddress(address);
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
    }

    @PutMapping("/address")
    public void updateAddress(@RequestBody Address address) {
        addressService.saveOrUpdate(address);
    }
}