package com.groupone.membershipgym.rest;

import com.groupone.membershipgym.entity.Admins;
import com.groupone.membershipgym.request.AdminRequest;
import com.groupone.membershipgym.response.AdminResponse;
import com.groupone.membershipgym.response.ResponseHandler;
import com.groupone.membershipgym.service.AdminServiceImpl;
import com.groupone.membershipgym.utils.DataException;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping ("/v1/api")
public class AdminRest {

    private static final Logger logger = LogManager.getLogger(AdminRest.class);
    private final AdminServiceImpl adminService;


    @PostMapping("/admin")
    public ResponseEntity<Object> createAdmin(@Valid @RequestBody AdminRequest adminRequest){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try{
            Admins newAdmin = adminRequest.convertToEntity();
            this.adminService.saveAdmin(newAdmin);
            AdminResponse adminResponse = newAdmin.convertToResponse();

            logger.info("================CREATE DATA==================");
            logger.info(newAdmin);
            logger.info("==============END CREATE DATA================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.CREATED,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), adminResponse);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        } catch (Exception e){
            logger.error("===========================================");
            logger.error(e.getMessage());
            logger.error("===========================================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        }
    }

    @GetMapping("/admins")
    public ResponseEntity<Object> getAllAdmins(){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            List<Admins> adminList = this.adminService.getAllAdmins();

            List<AdminResponse> adminResponses = adminList.stream()
                    .map(Admins::convertToResponse)
                    .collect(Collectors.toList());

            logger.info("================GET DATA==================");
            logger.info(adminList);
            logger.info("==============END GET DATA================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), adminResponses);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        } catch (Exception e) {

            logger.error("===========================================");
            logger.error(e.getMessage());
            logger.error("===========================================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        }
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Object> getOneAdmin(@PathVariable BigInteger id) {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try{

            Admins admin = this.adminService.getOneAdmin(id);
            AdminResponse adminResponse = admin.convertToResponse();

            logger.info("================GET ONE DATA==================");
            logger.info(admin);
            logger.info("==============END GET ONE DATA================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), adminResponse);
            return ResponseEntity.ok().headers(responseHeader).body(body);

        } catch (DataException e){

            logger.error("=============================================");
            logger.error(e.getMessage());
            logger.error("=============================================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable("id") BigInteger id, @RequestBody AdminRequest adminRequest){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {

            Admins admin = adminRequest.convertToEntity();
            admin.setAdminId(id);

            Admins updateAdmin = this.adminService.updateAdmin(admin);
            AdminResponse adminResponse = updateAdmin.convertToResponse();

            logger.info("================UPDATED DATA==================");
            logger.info(updateAdmin);
            logger.info("==============END UPDATED DATA================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), adminResponse);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        } catch (Exception e) {

            logger.error("=============================================");
            logger.error(e.getMessage());
            logger.error("=============================================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, responseHeader,ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);

        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deletAdmin(@PathVariable("id") BigInteger id){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Nama", "Membership App");
        try {
            this.adminService.deleteAdmin(id);
            logger.info("================DELETED DATA==================");
            logger.info("Data with id = " + id + " successfully delete");
            logger.info("==============END DELETED DATA================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse("", HttpStatus.OK,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "deleted" );
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);
        } catch (DataException e){

            logger.error("=============================================");
            logger.error(e.getMessage());
            logger.error("=============================================");

            ResponseEntity<Object> body = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND,responseHeader, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(responseHeader).body(body);
        }

    }

}