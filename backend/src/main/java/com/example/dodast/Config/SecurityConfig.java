package com.example.dodast.Config;

import com.example.dodast.Security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/advertisements/pending").hasRole("ADMIN")
                        .requestMatchers("/advertisements/*/approve", "/advertisements/*/reject").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/advertisements/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/advertisements/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/advertisements/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/advertisements/*").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/advertisements/*/sold").authenticated()
                        .requestMatchers("/favorite", "/favorite/**").authenticated()
                        .requestMatchers("/messages/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/health").permitAll()
                        .requestMatchers(HttpMethod.GET,"/search","/search/**").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}