package org.example.staffskillsauditor2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/skills/**").permitAll()
                        .requestMatchers("/portfolio/**").permitAll()
                        .requestMatchers("/staff/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}