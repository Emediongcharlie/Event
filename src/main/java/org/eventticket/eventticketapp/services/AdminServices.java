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
    RemoveEventResponse removeEventById(RemoveEventRequest request, int id);
    List<Event> viewEvent();
    List<Event> findEventByEventLocation(FindEventByLocationRequest request);
    FindEventByNameResponse findEventByName(FindEventByNameRequest request);
    FindEventByPriceResponse findEventByPrice(FindEventByPriceRequest request, double price);
    FindEventByCategoryResponse findEventByCategory(FindEventByCategoryRequest request);

}
