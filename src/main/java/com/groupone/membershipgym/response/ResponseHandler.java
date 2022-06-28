package com.groupone.membershipgym.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String errorMessage, HttpStatus status, HttpHeaders headers, ZonedDateTime accessedTime, Object responseObj){
        Map<String, Object> map = new HashMap<>();
        map.put("error_message", errorMessage);
        map.put("status", status.value());
        map.put("accessed_time", accessedTime.now());
        map.put("data", responseObj);

        return new ResponseEntity<>(map, headers, status);
    }
}