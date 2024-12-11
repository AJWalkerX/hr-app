package com.ajwalker.repository;

import com.ajwalker.entity.User;
import com.ajwalker.utility.Enum.user.EUserState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByEmail(String email);

    @Query(value = "SELECT U FROM User U WHERE U.userState IN (:states)")
    List<User> findAllUserByUserState(@Param("states") List<EUserState> states);

}