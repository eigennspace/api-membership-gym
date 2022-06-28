package com.groupone.membershipgym.repository;

import com.groupone.membershipgym.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AdminRepository extends JpaRepository<Admins, BigInteger> {
    Admins findByAdminId(BigInteger id);
}