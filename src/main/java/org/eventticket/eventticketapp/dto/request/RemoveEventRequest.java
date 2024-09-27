package org.eventticket.eventticketapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveEventRequest {


    private int eventId;
    private String eventName;
}
