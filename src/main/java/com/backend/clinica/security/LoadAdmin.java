package com.backend.clinica.security;

import com.backend.clinica.entity.UserModel;
import com.backend.clinica.entity.UserRole;
import com.backend.clinica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoadAdmin implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        String password = passwordEncoder.encode("admin");
        UserModel admin = new UserModel("admin", password, UserRole.ROLE_ADMIN);
        userService.save(admin);
        UserModel user = new UserModel("user", password, UserRole.ROLE_USER);
        userService.save(user);
    }
}
