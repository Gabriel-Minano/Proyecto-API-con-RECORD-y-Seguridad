package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecutiryConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivado para API Rest
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Todos los endpoints requieren autenticación
                )
                .httpBasic(Customizer.withDefaults()); // Habilita la autenticación básica (Usuario/Contraseña)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encriptador estándar BCrypt
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Creamos un usuario de prueba. La contraseña en texto plano es "password123"
        // pero la almacenamos hasheada usando el passwordEncoder.
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

}
