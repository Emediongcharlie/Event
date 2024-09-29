package org.eventticket.eventticketapp.controllers;

import org.apache.catalina.connector.Request;
import org.eventticket.eventticketapp.adminServices.AdminService;
import org.eventticket.eventticketapp.data.model.Admin;
import org.eventticket.eventticketapp.data.repository.AdminRepository;
import org.eventticket.eventticketapp.dtos.request.CreateLoginRequest;
import org.eventticket.eventticketapp.dtos.request.CreateLogoutRequest;
import org.eventticket.eventticketapp.dtos.request.CreateRegistrationRequest;
import org.eventticket.eventticketapp.dtos.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.RequestContextFilter;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RequestContextFilter requestContextFilter;

    @PostMapping("/register")
    public ResponseEntity<?>registerAdmin(@RequestBody CreateRegistrationRequest createRegistrationRequest) {
        try {
            RegistrationResponse result = adminService.register(createRegistrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody CreateLoginRequest createLoginRequest){
        try {
            var result = adminService.login(createLoginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutAdmin(@RequestBody CreateLogoutRequest createLogoutRequest){
        try {
            var result = adminService.logout(createLogoutRequest);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
