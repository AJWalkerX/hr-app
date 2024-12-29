package com.ajwalker.repository;

import com.ajwalker.entity.User;
import com.ajwalker.utility.Enum.user.EUserState;
import com.ajwalker.view.VwPermitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionalByEmail(String email);

    @Query(value = "SELECT U FROM User U WHERE U.userState IN (:states)")
    List<User> findAllUserByUserState(@Param("states") List<EUserState> states);
    
    Optional<User> findById(Long userId);

    @Query("SELECT U FROM User U WHERE U.companyId = ?1")
    List<User> findUsersByCompanyId(Long companyId);

    @Query("SELECT new com.ajwalker.view.VwPermitUser (U.id, U.avatar) FROM User U WHERE U.id IN(?1)")
    List<VwPermitUser> findAllUsersByUserIds(List<Long> userIdList);
}