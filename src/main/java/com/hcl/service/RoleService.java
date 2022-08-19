package com.hcl.service;
import com.hcl.model.Role;
import com.hcl.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository repo;
    public List<Role> getAllRoles() {
        return repo.findAll();
    }
    public Optional<Role> getRoleById(int id) {
        return repo.findById(id);
    }
    public void deleteRole(Integer id) {
        repo.deleteById(id);
    }
    public void addRole(Role role) {
        repo.save(role);
    }
    public void saveOrUpdate(Role role) {
        repo.save(role);
    }
}
