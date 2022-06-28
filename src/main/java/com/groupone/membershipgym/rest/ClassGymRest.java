package com.groupone.membershipgym.rest;

import com.groupone.membershipgym.entity.ClassGym;
import com.groupone.membershipgym.request.ClassGymRequest;
import com.groupone.membershipgym.response.ClassGymResponse;
import com.groupone.membershipgym.response.ResponseHandler;
import com.groupone.membershipgym.service.ClassGymServiceImpl;
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
@AllArgsConstructor
@RequestMapping("/v1/api")
public class ClassGymRest {

    private final ClassGymServiceImpl classGymService;

    @PostMapping("/class-gym")
    public ResponseEntity<Object> saveClass(@RequestBody ClassGymRequest classGymRequest){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            ClassGym newClass = classGymRequest.convertToEntity();
            this.classGymService.saveClassGym(newClass);

            ClassGymResponse response = newClass.convertToResponse();

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.CREATED,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), response);
            return ResponseEntity.ok().headers(responseHeader).body(body);

        } catch (Exception e){

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);
        }
    }

    @GetMapping("/classes-gym")
    public ResponseEntity<Object> getAllClasses(){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            List<ClassGym> classGyms = this.classGymService.getAllClassesGym();
            List<ClassGymResponse> classGymResponseList = classGyms.stream()
                    .map(ClassGym::convertToResponse)
                    .collect(Collectors.toList());

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), classGymResponseList);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        } catch (Exception e){


            ResponseEntity<Object> body = ResponseHandler.generateResponse("Data isn't available, please ask Admin to input new class", HttpStatus.BAD_GATEWAY,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "No Content");
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        }
    }

    @GetMapping("/classes-gym/{id}")
    public ResponseEntity<Object> getOneClass(@PathVariable ("id") Long id){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            ClassGym classGym = this.classGymService.getOneClassGym(id);
            ClassGymResponse response = classGym.convertToResponse();

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), response);
            return ResponseEntity.ok().headers(responseHeader).body(body);

        } catch (Exception e){

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        }
    }
}