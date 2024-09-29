package org.eventticket.eventticketapp.EventDTO.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByPriceResponse {

    private int eventId;
    private String eventName;
    private String location;
    private String description;
    private String price;
}
