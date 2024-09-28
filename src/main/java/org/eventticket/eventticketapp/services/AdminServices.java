package org.eventticket.eventticketapp.services;

import org.eventticket.eventticketapp.data.model.Event;
import org.eventticket.eventticketapp.dto.request.*;
import org.eventticket.eventticketapp.dto.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {


    BookEventResponse createEvent(BookEventRequest request);
    EventUpdateResponse updateEvent(EventUpdateRequest request, String name);
    RemoveEventResponse removeEventByName(RemoveEventRequest request, String eventName);
    List<Event> viewEvent();
    List<Event> findEventByEventLocation(String eventLocation);
    Event findEventByName(String eventName);
    List<String> findEventByPrice(String price);
}
