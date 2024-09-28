package org.eventticket.eventticketapp.adminServices;

import lombok.RequiredArgsConstructor;
import org.eventticket.eventticketapp.data.model.Admin;
import org.eventticket.eventticketapp.data.repository.AdminRepository;
import org.eventticket.eventticketapp.dtos.request.CreateLoginRequest;
import org.eventticket.eventticketapp.dtos.request.CreateLogoutRequest;
import org.eventticket.eventticketapp.dtos.request.CreateRegistrationRequest;
import org.eventticket.eventticketapp.dtos.response.LoginResponse;
import org.eventticket.eventticketapp.dtos.response.LogoutResponse;
import org.eventticket.eventticketapp.dtos.response.RegistrationResponse;
import org.eventticket.eventticketapp.exception.InvalidPassword;
import org.eventticket.eventticketapp.exception.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public RegistrationResponse register(CreateRegistrationRequest registrationRequest) {
        if(adminExist(registrationRequest.getUsername())) {
            throw new UserAlreadyExistException("user already exist");
        }
        Admin admin = new Admin();
        admin.setUsername(registrationRequest.getUsername());
        admin.setPassword(registrationRequest.getPassword());
        adminRepository.save(admin);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setMessage("Successfully registered");
        return registrationResponse;
    }

    @Override
    public List<Admin> getAllUsers() {
        return adminRepository.findAll();
    }

    @Override
    public LoginResponse login(CreateLoginRequest createLoginRequest) {
        Optional<Admin> foundAdmin = adminRepository.findByUsername(createLoginRequest.getUsername());
        if(foundAdmin.isEmpty()) {
            throw new InvalidPassword("invalid password");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully logged in");
        return loginResponse;
    }

    @Override
    public LogoutResponse logout(CreateLogoutRequest logoutRequest) {
        Optional<Admin> foundAdmin = adminRepository.findByUsername(logoutRequest.getUsername());
        if(foundAdmin.isEmpty()) {
            throw new UserAlreadyExistException("user already exist");
        }
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setMessage("Successfully logged out");
        return logoutResponse;
    }


    private boolean adminExist(String username) {
        return adminRepository.findByUsername(username).isPresent();
    }


}










