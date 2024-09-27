package org.eventticket.eventticketapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventUpdateRequest {

    private int eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String eventDateAndTime;

}
