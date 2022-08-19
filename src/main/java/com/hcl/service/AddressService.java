package com.hcl.service;
import com.hcl.model.Address;
import com.hcl.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository repo;
    public List<Address> getAllAddresses() {
        return repo.findAll();
    }
    public Optional<Address> getAddressById(int id) {
        return repo.findById(id);
    }
    public void deleteAddress(Integer id) {
        repo.deleteById(id);
    }
    public void addAddress(Address address) {
        repo.save(address);
    }
    public void saveOrUpdate(Address address) {
        repo.save(address);
    }
}
