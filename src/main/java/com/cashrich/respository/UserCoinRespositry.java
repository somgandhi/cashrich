package com.cashrich.respository;

import com.cashrich.model.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoinRespositry extends JpaRepository<ApiRequest,Long> {

}
