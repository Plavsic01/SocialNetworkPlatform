package com.plavsic.network.authorization.config;


import org.plavsic.security.config.CustomAccessDeniedHandler;
import org.plavsic.security.config.JwtAuthenticationEntryPoint;
import org.plavsic.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig extends org.plavsic.security.config.SecurityConfig {


    public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        super(customAccessDeniedHandler, jwtAuthenticationFilter, jwtAuthenticationEntryPoint);
    }

    @Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.disable())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/api/v1/auth/**").permitAll();
                authorize.requestMatchers("/**").permitAll(); // ERROR HANDLING
                authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();
                authorize.anyRequest().authenticated();
            });

        http.exceptionHandling(exception -> exception.accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
