package com.groupone.membershipgym.response;

import com.groupone.membershipgym.entity.TypeGymClass;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassGymResponse {

    private Long classId;

    private String className;

    private LocalDate scheduleDate;

    private LocalTime scheduleTime;

    private TypeGymClass typeOfClass;
}