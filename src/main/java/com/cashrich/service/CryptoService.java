package com.cashrich.service;

import com.cashrich.model.ApiRequest;
import com.cashrich.model.UserDetail;
import com.cashrich.respository.UserCoinRespositry;
import com.cashrich.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CryptoService {
    private static final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
    private static final String API_KEY = "27ab17d1-215f-49e5-9ca4-afd48810c149";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserCoinRespositry  userCoinRespositry ;

    @Autowired
    private UserRespository userRespository;

    public String sendCryptoData(String symbols,String request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                API_URL + "?symbol=" + symbols,
                HttpMethod.GET, entity, String.class
        );
        UserDetail byUserName = userRespository.findByUserName(getCurrentUserName());

        userCoinRespositry.save(new ApiRequest(byUserName,response.getBody() ,request , LocalDateTime.now()));

        return response.getBody();
    }

    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}



