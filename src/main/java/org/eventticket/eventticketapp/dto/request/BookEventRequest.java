package org.eventticket.eventticketapp.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
