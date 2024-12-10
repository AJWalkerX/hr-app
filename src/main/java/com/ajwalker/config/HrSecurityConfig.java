package com.ajwalker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class HrSecurityConfig {
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req ->{
            req
//                   TODO: .requestMatchers("admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll();
        });
//?        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll); -> bunu arastir
       http.csrf(AbstractHttpConfigurer::disable);
//  TODO:     http.addFilterBefore()
        return http.build();
   }
}
