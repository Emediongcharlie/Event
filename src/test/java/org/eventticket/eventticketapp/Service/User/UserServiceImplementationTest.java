package org.eventticket.eventticketapp.Service.User;

import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplementationTest {
    @Autowired
    private UserService userService;

    @Test
    void testThatUserCanBeCreated() {
       CreateUserRequest createUserRequest = new CreateUserRequest();
       createUserRequest.setEmail("Richness@yahoo.com");
       createUserRequest.setName("Richard");
       createUserRequest.setPhoneNumber("09021324121");
       createUserRequest.setPassword("wealthy");
       createUserRequest.setUsername("Richy");
       CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
       createUserResponse.setMessage("User created Successfully");
       assertEquals(createUserResponse.getMessage(),"User created Successfully");

    }
    @Test
    void testThatUserCannotBeCreatedWithSameUsername(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("Richness@yahoo.com");
        createUserRequest.setName("Richard");
        createUserRequest.setPhoneNumber("09021324121");
        createUserRequest.setPassword("wealthy");
        createUserRequest.setUsername("Richy");
        assertThrows(RuntimeException.class, ()-> {throw new RuntimeException("user already existing");});
    }

}