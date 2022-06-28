package com.groupone.membershipgym.response;

import com.groupone.membershipgym.entity.ClassGym;
import com.groupone.membershipgym.entity.Users;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private BigInteger bookingId;

    private Users user;

    private ClassGym classGyms;

}