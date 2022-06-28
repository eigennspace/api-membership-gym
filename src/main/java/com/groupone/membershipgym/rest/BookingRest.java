package com.groupone.membershipgym.rest;

import com.groupone.membershipgym.entity.Booking;
import com.groupone.membershipgym.request.BookingRequest;
import com.groupone.membershipgym.response.BookingResponse;
import com.groupone.membershipgym.response.ResponseHandler;
import com.groupone.membershipgym.service.BookingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api")
@AllArgsConstructor

public class BookingRest {
    private final BookingServiceImpl bookingService;

    @PostMapping("/booking")
    public ResponseEntity<Object> createBooking(@RequestBody BookingRequest request){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            Booking booking = new Booking();
            this.bookingService.saveBooking(booking, request);

            BookingResponse response = booking.convertToResponse();

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.CREATED, responseHeader,ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), response);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        } catch (Exception e){
            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);
        }
    }

    @GetMapping("/bookings")
    public ResponseEntity<Object> getAllBookings(){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");

        try {
            List<Booking> bookingList = this.bookingService.getAllBookings();

            List<BookingResponse> bookingResponses = bookingList.stream()
                    .map(Booking::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseHandler.generateResponse("", HttpStatus.OK, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), bookingResponses);
        } catch (Exception e) {

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);
        }

    }
}