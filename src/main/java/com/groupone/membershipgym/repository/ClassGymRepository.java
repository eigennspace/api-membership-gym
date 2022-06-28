package com.groupone.membershipgym.repository;

import com.groupone.membershipgym.entity.ClassGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGymRepository extends JpaRepository<ClassGym, Long> {
    ClassGym findByClassId(Long id);
}