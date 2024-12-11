package com.ajwalker.config;

import com.ajwalker.constant.RestApis;
import com.ajwalker.utility.Enum.EAdminRole;
import com.ajwalker.utility.Enum.user.EUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.authenticated;

@Configuration
@Slf4j
public class HrSecurityConfig {

    @Bean
    public JWTTokenFilter getJwtTokenFilter() {
        return new JWTTokenFilter();
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req ->{
            req
                   .requestMatchers(
                            "/swagger-ui/**", "/v3/api-docs/**",
                            "/v1/dev/user/register", "v1/dev/user/login"
                           ,"v1/dev/admin/login"
                    )
                    .permitAll()
                    .requestMatchers("/admin/**", "/user/**").hasAuthority(EAdminRole.ADMIN.toString())
                    .requestMatchers("/manager/**").hasAuthority(EUserRole.MANAGER.toString())
                    .requestMatchers("/personel/**").hasAuthority(EUserRole.PERSONAL.toString())
//                  .authenticated();
                    .anyRequest().permitAll();
        });
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
       http.csrf(AbstractHttpConfigurer::disable);
       http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
   }

   @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
   }
}
