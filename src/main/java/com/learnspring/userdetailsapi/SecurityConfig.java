// package com.learnspring.userdetailsapi;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class SecurityConfig {
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/users/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll())
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//         return http.build();
//     }
// }
