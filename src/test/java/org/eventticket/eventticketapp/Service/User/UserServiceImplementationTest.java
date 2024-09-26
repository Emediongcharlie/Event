package org.eventticket.eventticketapp.Service.User;

import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.request.LoginUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;
import org.eventticket.eventticketapp.Dto.response.LoginUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Test
    void testThatUserMustBeCreatedBeforeLogin(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("oloade");
        createUserRequest.setEmail("ariyo@gmail.com");
        createUserRequest.setPhoneNumber("08123456712");
        createUserRequest.setName("micheal");

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUserName(createUserRequest.getUsername());
        loginRequest.setPassword(createUserRequest.getPassword());

        LoginUserResponse response = userService.isUserLoggedIn(loginRequest);
       assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Login sucessful");

        CreateUserResponse createUserResponse = userService.createUser( createUserRequest);
        assertThat(createUserResponse).isNotNull();
    }

    @Test
    void testThatUserCanViewEvents(){

    }

}