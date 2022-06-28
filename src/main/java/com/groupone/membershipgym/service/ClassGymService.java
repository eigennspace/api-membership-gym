package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.ClassGym;
import com.groupone.membershipgym.utils.DataException;

import java.util.List;

public interface ClassGymService {
    List<ClassGym> getAllClassesGym();

    ClassGym getOneClassGym(Long id) throws DataException;

    ClassGym saveClassGym(ClassGym classGym);

    ClassGym updateClassGym(ClassGym classGym) throws DataException;

    void deleteClassGym(Long id) throws DataException;
}