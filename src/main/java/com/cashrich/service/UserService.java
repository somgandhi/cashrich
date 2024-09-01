package com.cashrich.service;

import com.cashrich.model.UserDetail;
import com.cashrich.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final private UserRespository userRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public boolean findByUserName(String userName) {
        return  findByUserName1(userName) != null;
    }
    public UserDetail findByUserName1(String userName) {
        return  userRespository.findByUserName(userName);
    }

    public void createUser(UserDetail user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRespository.save(user);
    }
}
