package com.backend.clinica.security;

import com.backend.clinica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigWebSecurity {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                .requestMatchers("/").permitAll()
                .requestMatchers("/routes/odontologos/**").hasRole("ADMIN")
                .requestMatchers("/routes/pacientes/**").hasRole("ADMIN")
                .requestMatchers("/routes/turnos/list.html").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/routes/turnos/**").hasRole("ADMIN")
                .requestMatchers(("/h2-console/**")).hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(Customizer.withDefaults()).logout(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) -> headers.frameOptions((frame) -> frame.disable()));

        return http.build();
    }
}
