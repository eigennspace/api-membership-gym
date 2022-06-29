package com.groupone.membershipgym.controller;

import com.groupone.membershipgym.entity.Users;
import com.groupone.membershipgym.request.UserRequest;
import com.groupone.membershipgym.rest.AdminRest;
import com.groupone.membershipgym.rest.UserRest;
import com.groupone.membershipgym.service.UserServiceImpl;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class WelcomeController {

    private static final Logger logger = LogManager.getLogger(AdminRest.class);
    private final UserServiceImpl userService;

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
    public ResponseEntity<?> submit(@ModelAttribute("user") UserRequest userRequest) throws DataException {
        UserRest rest = new UserRest(userService);

        return rest.createUser(userRequest);
    }
}