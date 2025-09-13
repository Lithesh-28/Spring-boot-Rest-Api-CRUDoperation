package com.litheshShetty.springBootRESTApiDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails1 = User.withUsername("Admin")
                .password(passwordEncoder().encode("lith@123")).build();
        UserDetails userDetails2 = User.withUsername("user1")
                .password(passwordEncoder().encode("pass1")).build();
        UserDetails userDetails3 = User.withUsername("user2")
                .password("pass2").build();

        return new InMemoryUserDetailsManager(userDetails1,userDetails2,userDetails3);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // open
                        .anyRequest().authenticated() // secured
                )
                .httpBasic(Customizer.withDefaults()); // enable Basic Auth
        return http.build();
    }
}
