package org.eventticket.eventticketapp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRegistrationRequest {

    private Long id;
    private String username;
    private String password;
}
