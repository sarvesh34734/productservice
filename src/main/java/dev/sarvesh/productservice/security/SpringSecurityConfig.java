package dev.sarvesh.productservice.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/actuator/health")
                        .permitAll()
//                        .requestMatchers("/api/v1/products/")
//                        .hasAuthority("SELLER")
                        .anyRequest()
                        .permitAll()
                );
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
//                        // use custom converter
//                        jwtConfigurer -> jwtConfigurer
//                                .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())
//                ));
        return http.csrf().disable().cors().disable().build();
    }

}
