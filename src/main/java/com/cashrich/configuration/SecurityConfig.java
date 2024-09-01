package com.cashrich.configuration;


import com.cashrich.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {


    @Bean
    public SecurityFilterChain settingHttpSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requestMachers -> {
           requestMachers.requestMatchers("/update","test","login1","update-coin").authenticated();
            requestMachers.anyRequest().permitAll();
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic();
    return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider  authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
