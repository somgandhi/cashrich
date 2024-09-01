package com.cashrich.controller;


import com.cashrich.model.UserDetail;
import com.cashrich.respository.UserRespository;
import com.cashrich.service.CryptoService;
import com.cashrich.service.UserDetailsServiceImpl;
import com.cashrich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class UserController {



    private UserRespository userRespository;
    private UserDetailsServiceImpl userDetailsService;

    private UserService userService;

    private CryptoService cryptoService;


    @Autowired
    public UserController(UserRespository userRespository, UserDetailsServiceImpl userDetailsService, UserService userService, CryptoService cryptoService) {
        this.userRespository = userRespository;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.cryptoService = cryptoService;
    }




    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody UserDetail user) {

        boolean isUserExist = userService.findByUserName(user.getUserName());
        if (isUserExist)
            return new ResponseEntity<>("Username is already exist", HttpStatus.BAD_REQUEST);

        userService.createUser(user);
        return new ResponseEntity<>("User created Successfully", HttpStatus.CREATED);
    }
@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDetail UserDetails){
//    UserDetails userDetails = userDetailsService.loadUserByUsername(UserDetails.getUserName());
//    boolean isUserExist =userService.findByUserName(UserDetails.getUserName());
//        if (userDetails == null)
//            return new ResponseEntity<>("Wrong", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Login sucesss", HttpStatus.OK);
    }

    @PostMapping("/update-coin")
    public ResponseEntity<String>  setCoinUserData(@RequestBody UserDetail userDetail) {
        String s = "";
        try {
            s = cryptoService.sendCryptoData("BTC,ETC,LTC","");
        } catch (Exception e) {
            return new ResponseEntity<>(" Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
