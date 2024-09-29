package org.eventticket.eventticketapp.EventDTO.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookEventRequest {


    private int eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String eventDateAndTime;
    private String price;

}
