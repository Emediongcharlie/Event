package org.eventticket.eventticketapp.Service.User;

import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;
import org.eventticket.eventticketapp.data.model.User;
import org.eventticket.eventticketapp.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.findByUsername(createUserRequest.getUsername());
        if (user != null) throw new RuntimeException("User already existing");

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

}
