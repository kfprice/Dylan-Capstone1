package com.hcl.service;

import com.hcl.model.Role;
import com.hcl.model.User;
import com.hcl.request.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    public String register(RegistrationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setContact(request.getContact());
        user.setSSN(request.getSSN());
        user.setUserAddresses(request.getUserAddresses());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userService.signUpUser(user);
    }
    public String registerAdmin(RegistrationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setContact(request.getContact());
        user.setSSN(request.getSSN());
        user.setUserAddresses(request.getUserAddresses());
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        return userService.signUpUser(user);
    }
}
