package org.eventticket.eventticketapp.EventDTO.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventUpdateResponse {

    private int eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String eventDateAndTime;
    private String message;
}
