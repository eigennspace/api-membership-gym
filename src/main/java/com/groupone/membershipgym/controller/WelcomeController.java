package com.groupone.membershipgym.controller;

import com.groupone.membershipgym.entity.Users;
import com.groupone.membershipgym.repository.UserRepository;
import com.groupone.membershipgym.response.ResponseHandler;
import com.groupone.membershipgym.response.UserResponse;
import com.groupone.membershipgym.rest.AdminRest;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
@AllArgsConstructor
public class WelcomeController {

    private static final Logger logger = LogManager.getLogger(AdminRest.class);
    private final UserRepository userRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String showForm(Model model){
        Users user = new Users();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> submit(@ModelAttribute("user") Users user){

        this.userRepository.save(user);
        logger.info("Success create " + user);

        UserResponse response = user.convertToResponse();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Name", "GYM MEMBERSHIP APP");

        ResponseEntity<?> body = ResponseHandler.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")) ,response);
        return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
    }
}