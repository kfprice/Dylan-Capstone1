package com.hcl.controller;

import com.hcl.model.User;
import com.hcl.request.RegistrationRequest;
import com.hcl.service.RegistrationService;
import com.hcl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private RegistrationService registrationService;

    @GetMapping("/admin/user")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/user/{id}")
    public Optional<User> getUserByID(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/admin/user")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @DeleteMapping("/admin/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/admin/user/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User updateUser= userService.getUserById(id).get();
        if(updateUser != null) {
            updateUser.setFirstName(user.getFirstName());
            updateUser.setLastName(user.getLastName());
            updateUser.setEmail(user.getEmail());
            updateUser.setUsername(user.getUsername());
            updateUser.setPassword(user.getPassword());
            updateUser.setContact(user.getContact());
            updateUser.setSSN(user.getSSN());
            updateUser.setUserAddresses(user.getUserAddresses());
            userService.saveOrUpdate(updateUser);
        }
    }

    @GetMapping("/user/{id}")
    public Optional<User> getOwnAccount(@PathVariable("id") Integer id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (((User) principal).getUserId() == id) {
            return userService.getUserById(id);
        }
        return null;
    }

    @DeleteMapping("user/{id}")
    public void deleteOwnAccount(@PathVariable("id") Integer id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (((User) principal).getUserId() == id) {
            userService.deleteUser(id);
        }
    }

    @PutMapping("/user/{id}")
    public void updateOwnAccount(@PathVariable("id") Integer id, @RequestBody User user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (((User) principal).getUserId() == id) {
            updateUser(id, user);
        }
    }
}