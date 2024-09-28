package org.eventticket.eventticketapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveEventResponse {

    private int eventId;
    private String message;
}
