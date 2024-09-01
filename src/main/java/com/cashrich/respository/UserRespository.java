package com.cashrich.respository;


import com.cashrich.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<UserDetail,Long> {

   public UserDetail findByUserName(String userName);
}
