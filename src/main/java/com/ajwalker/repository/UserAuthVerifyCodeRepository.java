package com.ajwalker.repository;


import com.ajwalker.entity.UserAuthVerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAuthVerifyCodeRepository extends JpaRepository<UserAuthVerifyCode, Long> {

    @Query(value = "SELECT U.userId FROM UserAuthVerifyCode U WHERE  U.code  = :authCode  order by U.create_at DESC LIMIT 1")
    Optional<Long> findLastUserIdByAuthCode( @Param("authCode") String authCode);
}
