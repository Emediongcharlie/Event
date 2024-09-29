package org.eventticket.eventticketapp.EventDTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByLocationRequest {
    private String Location;
    private String eventName;
}
