package com.groupone.membershipgym.rest;

import com.groupone.membershipgym.entity.Newsletter;
import com.groupone.membershipgym.request.NewsletterRequest;
import com.groupone.membershipgym.response.NewsletterResponse;
import com.groupone.membershipgym.response.ResponseHandler;
import com.groupone.membershipgym.service.NewsletterServiceImpl;
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
public class NewsletterRest {

    private final NewsletterServiceImpl newsletterService;

    @PostMapping("/newsletter")
    public ResponseEntity<Object> createNewsletter(@RequestBody NewsletterRequest request){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            Newsletter newsletter = new Newsletter();

            this.newsletterService.createNewsletter(newsletter, request);

            NewsletterResponse response = newsletter.convertToResponse();

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.CREATED,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), response);
            return ResponseEntity.ok().headers(responseHeader).body(body);

        } catch (Exception e){

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.badRequest().headers(responseHeader).body(body);

        }
    }

    @GetMapping("/newsletters")
    public ResponseEntity<Object> getAllNewsletters(){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            List<Newsletter> newsletterList = this.newsletterService.getAllNewsletter();

            List<NewsletterResponse> responses = newsletterList.stream()
                    .map(Newsletter::convertToResponse)
                    .collect(Collectors.toList());

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK, responseHeader,ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responses);
            return ResponseEntity.ok().headers(responseHeader).body(body);

        } catch (Exception e){

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).headers(responseHeader).body(body);

        }
    }

}