package org.eventticket.eventticketapp.Service.User;

import org.eventticket.eventticketapp.Dto.request.CreateUserRequest;
import org.eventticket.eventticketapp.Dto.response.CreateUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest);
}
