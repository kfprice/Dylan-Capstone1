package com.hcl.service;
import com.hcl.model.User;
import com.hcl.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSender emailSender;

    public Optional<User> getUserById(int id) {
        return repo.findById(id);
    }
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }
    public void addUser(User user) {
        repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public void saveOrUpdate(User user) {
        repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return repo.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }

    public String signUpUser(User user) {
        boolean usernameExists = repo.findByUsername(user.getUsername()).isPresent();
        if(usernameExists) {
            throw new IllegalStateException("Username already taken");
        }
        boolean emailExists = repo.findByEmail(user.getEmail()).isPresent();
        if(emailExists) {
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        emailSender.send(user.getEmail(), "Welcome "+ user.getUsername());

        return "Registered";
    }
}
