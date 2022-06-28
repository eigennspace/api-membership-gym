package com.groupone.membershipgym.response;

import com.groupone.membershipgym.entity.LevelAdmin;
import lombok.*;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResponse {

    private BigInteger adminId;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private LevelAdmin level;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}