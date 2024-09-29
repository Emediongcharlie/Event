package org.eventticket.eventticketapp.EventDTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByNameRequest {

    private int eventId;
    private String eventName;
    private String location;
    private String description;
    private String price;
}
