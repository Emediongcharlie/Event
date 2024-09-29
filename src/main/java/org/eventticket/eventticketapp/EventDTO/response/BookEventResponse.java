package org.eventticket.eventticketapp.EventDTO.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class BookEventResponse {

    @Id
    private String id;
    private String eventName;
    private String eventDateAndTime;
    private String eventLocation;
    private String eventDescription;
    private String price;

}
