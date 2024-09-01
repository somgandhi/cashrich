package com.cashrich.service;

import com.cashrich.model.UserDetail;
import com.cashrich.respository.UserRespository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail user = userRespository.findByUserName(username);
            if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return User.withUsername(user.getUserName()).password(user.getPassword()).authorities("ADMIN").build();
    }
}
