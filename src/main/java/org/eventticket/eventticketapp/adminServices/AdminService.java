package org.eventticket.eventticketapp.adminServices;

import org.eventticket.eventticketapp.data.model.Admin;
import org.eventticket.eventticketapp.dtos.request.CreateLoginRequest;
import org.eventticket.eventticketapp.dtos.request.CreateLogoutRequest;
import org.eventticket.eventticketapp.dtos.request.CreateRegistrationRequest;
import org.eventticket.eventticketapp.dtos.response.LoginResponse;
import org.eventticket.eventticketapp.dtos.response.LogoutResponse;
import org.eventticket.eventticketapp.dtos.response.RegistrationResponse;

import java.util.List;

public interface AdminService {
    RegistrationResponse register(CreateRegistrationRequest registrationRequest);

    List<Admin> getAllUsers();

    LoginResponse login(CreateLoginRequest createLoginRequest);

    LogoutResponse logout(CreateLogoutRequest logoutRequest);
}
