package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Admins;
import com.groupone.membershipgym.entity.Booking;
import com.groupone.membershipgym.request.BookingRequest;
import com.groupone.membershipgym.utils.DataException;

import java.math.BigInteger;
import java.util.List;

public interface AdminService {
    List<Admins> getAllAdmins();

    Admins saveAdmin(Admins admin);

    Admins getOneAdmin(BigInteger id) throws DataException;

    Admins updateAdmin(Admins admin) throws DataException;

    void deleteAdmin(BigInteger id) throws DataException;
}