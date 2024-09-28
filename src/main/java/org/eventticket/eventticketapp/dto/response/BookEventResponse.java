package org.eventticket.eventticketapp.dto.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
