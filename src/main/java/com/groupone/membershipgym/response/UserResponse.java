package com.groupone.membershipgym.response;

import lombok.*;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}