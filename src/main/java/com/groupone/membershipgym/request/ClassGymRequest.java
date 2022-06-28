package com.groupone.membershipgym.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.groupone.membershipgym.entity.ClassGym;
import com.groupone.membershipgym.entity.TypeGymClass;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassGymRequest {

    @NotEmpty(message = "Class Name cannot be empty")
    private String className;

    @NotEmpty(message = "Schedule Time cannot be empty")
    private LocalDate scheduledDate;

//    @NotEmpty(message = "Schedule Time cannot be empty")
//    @JsonIgnoreProperties(value = {
//            "hour","minute", "second", "nano","{}"
//    })
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonDeserialize(as = LocalTime.class)
//    @Past
    @NotEmpty(message = "Schedule Time cannot be empty")
    private String scheduledTime;

    @NotEmpty(message = "Type of Gym cannot be empty")
    private TypeGymClass typeOfClass;

    public ClassGym convertToEntity(){
        return ClassGym.builder().className(this.className).scheduledDate(this.scheduledDate).scheduledTime(LocalTime.parse(this.scheduledTime)).typeOfClass(this.typeOfClass).build();
    }
}