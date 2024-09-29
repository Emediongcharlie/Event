package org.eventticket.eventticketapp.Dto.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String UserName;
    private String password;
}
