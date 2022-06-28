package com.groupone.membershipgym.repository;

import com.groupone.membershipgym.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsCurrentUserByUserName(String userName);

    Users findByUserId(Long id);
}