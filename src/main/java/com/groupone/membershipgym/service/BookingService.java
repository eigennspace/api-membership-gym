package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Booking;
import com.groupone.membershipgym.request.BookingRequest;
import com.groupone.membershipgym.utils.DataException;

import java.math.BigInteger;
import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();

    Booking saveBooking(Booking booking, BookingRequest request);

    Booking getOneBooking(BigInteger id) throws DataException;

    Booking updateBooking(Booking booking) throws DataException;

    void deleteBooking(Booking booking) throws DataException;
}