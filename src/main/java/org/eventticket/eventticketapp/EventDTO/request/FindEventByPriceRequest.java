package org.eventticket.eventticketapp.EventDTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEventByPriceRequest {

    private String price;
}
