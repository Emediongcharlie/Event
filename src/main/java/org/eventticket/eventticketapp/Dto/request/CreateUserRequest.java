package org.eventticket.eventticketapp.Dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
