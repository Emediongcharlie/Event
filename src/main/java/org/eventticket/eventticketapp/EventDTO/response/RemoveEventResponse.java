package org.eventticket.eventticketapp.EventDTO.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveEventResponse {

    private int eventId;
    private String message;
}
