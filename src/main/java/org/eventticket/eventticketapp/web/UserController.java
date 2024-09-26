package org.eventticket.eventticketapp.web;

import lombok.RequiredArgsConstructor;
import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;
import org.eventticket.eventticketapp.Service.User.UserService;
import org.eventticket.eventticketapp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        try{
            CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
            return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
