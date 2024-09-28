package org.eventticket.eventticketapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByNameRequest {

    private String eventName;
}
