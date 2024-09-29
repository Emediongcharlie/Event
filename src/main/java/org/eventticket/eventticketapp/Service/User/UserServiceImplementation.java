package org.eventticket.eventticketapp.Service.User;

import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.request.LoginUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;
import org.eventticket.eventticketapp.Dto.response.LoginUserResponse;
import org.eventticket.eventticketapp.data.model.User;
import org.eventticket.eventticketapp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private static final int MIN_LENGTH = 6;

    @Autowired
    private UserRepository userRepository;


    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.findByUsername(createUserRequest.getUsername());
        if (user != null) throw new RuntimeException("User already existing");
        //validUsername(createUserRequest.getUsername());
        //validatePasswordLength(createUserRequest.getPassword());

        User newUser = new User();
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setName(createUserRequest.getName());
        newUser.setPassword(createUserRequest.getPassword());
        newUser.setPhoneNumber(createUserRequest.getPhoneNumber());
        newUser.setUsername(createUserRequest.getUsername());
        userRepository.save(newUser);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("User created Successfully");
        return createUserResponse;
    }
    private void validUsername(String username){
        if(username == null || username.trim().isEmpty()) throw new RuntimeException("empty username");
    }

    private void validatePasswordLength(String password){
        if(password == null || password.trim().length() <= MIN_LENGTH) throw new RuntimeException("invalid password length");
    }

    @Override
    public LoginUserResponse isUserLoggedIn(LoginUserRequest loginRequest) {

        validUsername(loginRequest.getUserName());
        validatePasswordLength(loginRequest.getPassword());
        User user =userRepository.findByUsername(loginRequest.getUserName());
        if (user == null) throw new RuntimeException("not a registered user");

        user.setUsername(loginRequest.getUserName());
        user.setPassword(loginRequest.getPassword());

        LoginUserResponse response = new LoginUserResponse();
        response.setMessage("Login successful");
        return response;
    }

    }

