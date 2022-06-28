package com.groupone.membershipgym.service;

import com.groupone.membershipgym.entity.Booking;
import com.groupone.membershipgym.entity.ClassGym;
import com.groupone.membershipgym.entity.Users;
import com.groupone.membershipgym.repository.BookingRepository;
import com.groupone.membershipgym.repository.ClassGymRepository;
import com.groupone.membershipgym.repository.UserRepository;
import com.groupone.membershipgym.request.BookingRequest;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final ClassGymRepository classGymRepository;

    @Override
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking saveBooking(Booking booking, BookingRequest request) {

        booking.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));
        booking.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")));

        Users users = this.userRepository.findByUserId(request.getUserId());
        booking.setUser(users);

        ClassGym classGym = this.classGymRepository.findByClassId(request.getClassId());
        booking.setClassGyms(classGym);

        booking.getClassGyms().setClassId(request.getClassId());

        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking getOneBooking(BigInteger id) throws DataException {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking==null){
            throw new DataException("Booking is not found");
        }
        return booking.get();
    }

    @Override
    public Booking updateBooking(Booking booking) throws DataException {

        this.getOneBooking(booking.getBooking_id());

        return this.bookingRepository.save(booking);

    }

    @Override
    public void deleteBooking(Booking booking) throws DataException {
        Booking deletedBooking = this.getOneBooking(booking.getBooking_id());

        this.bookingRepository.delete(deletedBooking);
    }
}