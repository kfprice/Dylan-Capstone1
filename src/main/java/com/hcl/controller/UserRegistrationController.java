package com.hcl.controller;

import com.hcl.request.RegistrationRequest;
import com.hcl.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserRegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    @PostMapping("/registration/admin")
    public String registerAdmin(@RequestBody RegistrationRequest request) {
        return registrationService.registerAdmin(request);
    }
}
