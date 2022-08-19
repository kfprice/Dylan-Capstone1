package com.hcl.controller;

import com.hcl.model.Role;
import com.hcl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }
    @GetMapping("/role/{id}")
    public Optional<Role> getRoleByID(@PathVariable Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/role")
    public void addRole(@RequestBody Role role) {
        roleService.addRole(role);
    }

    @DeleteMapping("/role/{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
    }

    @PutMapping("/role")
    public void updateRole(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
    }
}