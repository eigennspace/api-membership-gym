package com.groupone.membershipgym.rest;

import com.groupone.membershipgym.entity.Users;
import com.groupone.membershipgym.request.UserRequest;
import com.groupone.membershipgym.response.ResponseHandler;
import com.groupone.membershipgym.response.UserResponse;
import com.groupone.membershipgym.service.UserServiceImpl;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping ("/v1/api")

public class UserRest {

    private static final Logger logger = LogManager.getLogger(UserRest.class);
    private final UserServiceImpl userService;


    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");

        try{
            Users newUsers = userRequest.convertToEntity();
            this.userService.saveUser(newUsers);
            UserResponse userResponse = newUsers.convertToResponse();

            logger.info("================CREATE DATA==================");
            logger.info(newUsers);
            logger.info("==============END CREATE DATA================");

            //Another way to return Response Entity with headers is showing in the body
            var body= ResponseHandler.generateResponse("", HttpStatus.CREATED, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponse);
            return ResponseEntity.ok().headers(responseHeader).body(body);
//            return ResponseHandler.generateResponse("", HttpStatus.CREATED, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponse);

        } catch (Exception e){
            logger.error("===========================================");
            logger.error(e.getMessage());
            logger.error("===========================================");

            var body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.badRequest().headers(responseHeader).body(body);
//          return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers(){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            List<Users> usersList = this.userService.getAllUsers();

            List<UserResponse> userResponseList = usersList.stream()
                    .map(Users::convertToResponse)
                    .collect(Collectors.toList());

            logger.info("================GET DATA==================");
            logger.info(usersList);
            logger.info("==============END GET DATA================");

            var body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponseList);
            return ResponseEntity.ok().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponseList);

        } catch (Exception e) {

            logger.error("===========================================");
            logger.error(e.getMessage());
            logger.error("===========================================");

            var body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.badRequest().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);

        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable Long id) {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try{

            Users user = this.userService.getOneUser(id);
            UserResponse userResponse = user.convertToResponse();

            logger.info("================GET ONE DATA==================");
            logger.info(user);
            logger.info("==============END GET ONE DATA================");

            var body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponse);
            return ResponseEntity.ok().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponse);

        } catch (DataException e){

            logger.error("=============================================");
            logger.error(e.getMessage());
            logger.error("=============================================");

            var body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.badRequest().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);

        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            Users users = userRequest.convertToEntity();
            users.setUserId(id);

            Users updatedUser = this.userService.updateUser(users);
            UserResponse userResponse = updatedUser.convertToResponse();

            logger.info("================UPDATED DATA==================");
            logger.info(updatedUser);
            logger.info("==============END UPDATED DATA================");

            var body = ResponseHandler.generateResponse("",HttpStatus.OK ,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponse);
            return ResponseEntity.ok().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), userResponse);

        } catch (Exception e) {

            logger.error("=============================================");
            logger.error(e.getMessage());
            logger.error("=============================================");

            var body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.badRequest().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {
            this.userService.deleteUser(id);
            logger.info("================DELETED DATA==================");
            logger.info("Data with id = " + id + " successfully delete");
            logger.info("==============END DELETED DATA================");

            var body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "deleted" );
            return ResponseEntity.ok().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "deleted" );
        } catch (DataException e){

            logger.error("=============================================");
            logger.error(e.getMessage());
            logger.error("=============================================");

            var body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.badRequest().headers(responseHeader).body(body);

//            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
        }

    }

}