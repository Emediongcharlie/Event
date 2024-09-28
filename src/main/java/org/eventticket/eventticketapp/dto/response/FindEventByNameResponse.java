package org.eventticket.eventticketapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByNameResponse {


    private int eventId;
    private String eventName;
    private String location;
    private String description;
    private String price;
}
