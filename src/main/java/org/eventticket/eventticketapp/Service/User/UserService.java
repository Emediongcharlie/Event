package org.eventticket.eventticketapp.Service.User;

import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.request.LoginUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;
import org.eventticket.eventticketapp.Dto.response.LoginUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    LoginUserResponse isUserLoggedIn(LoginUserRequest loginRequest);
}
