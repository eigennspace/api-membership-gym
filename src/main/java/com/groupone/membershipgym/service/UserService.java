package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Users;
import com.groupone.membershipgym.utils.DataException;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    List<Users> getAllUsers();

    Users getOneUser(Long id) throws DataException;

    Users saveUser(Users users) throws DataException;

    Users updateUser(Users users) throws DataException;

    void deleteUser(Long id) throws DataException;
}