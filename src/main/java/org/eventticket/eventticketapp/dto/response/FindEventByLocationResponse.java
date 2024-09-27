package org.eventticket.eventticketapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByLocationResponse {

    private int eventId;
    private String eventName;
    private String location;
    private String description;

}
