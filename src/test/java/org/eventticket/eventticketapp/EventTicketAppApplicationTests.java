package org.eventticket.eventticketapp;

import org.eventticket.eventticketapp.data.model.Admin;
import org.eventticket.eventticketapp.data.model.Event;
import org.eventticket.eventticketapp.data.repository.EventRepository;
import org.eventticket.eventticketapp.dto.request.*;
import org.eventticket.eventticketapp.dto.response.*;
import org.eventticket.eventticketapp.services.AdminServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class EventTicketAppApplicationTests {

    @Autowired
    private AdminServices adminServices;
    @Autowired
    private EventRepository eventRepository;

//    @BeforeEach
//    void setUp() {
//        eventRepository.deleteAll();
//    }

    @Test
    public void testAdminCanCreateEvent() {
        BookEventRequest request = new BookEventRequest();
        request.setEventName("tech hangout");
        request.setEventDescription("to get all techie together");
        request.setEventDateAndTime("4/3/2024");
        request.setEventLocation("Lagos");
        request.setPrice("2000");
        BookEventResponse response = adminServices.createEvent(request);
        assertThat(response).isNotNull();
        assertThat(response.getEventName().contains("tech hangout"));
    }

    @Test
    public void testOneEventCannotBeCreatedTwice(){
        BookEventRequest request = new BookEventRequest();
        request.setEventName("techlogy");
        request.setEventDescription("explore the world of tech");
        request.setEventDateAndTime("4/12/2024");
        request.setEventLocation("Lagos");
        request.setPrice("10000");
        BookEventResponse response = adminServices.createEvent(request);
        assertThat(response).isNotNull();
    }

    @Test
    public void testEventCanBeUpdated(){
        BookEventRequest request = new BookEventRequest();
        request.setEventName("tech journey");
        request.setEventDescription("to get all techie together");
        request.setEventDateAndTime("4/3/2024");
        request.setEventLocation("Lagos");
        request.setPrice("2000");
        BookEventResponse response = adminServices.createEvent(request);
        assertThat(response).isNotNull();
        EventUpdateRequest request1 = new EventUpdateRequest();
        request1.setEventName("tech journey");
        request1.setEventDescription("techie together");
        request1.setEventDateAndTime("4/3/2024");
        request1.setEventLocation("Abuja");
        EventUpdateResponse response1 = adminServices.updateEvent(request1, response.getEventName());
        assertThat(response1).isNotNull();
        assertThat(response1.getEventDescription()).contains("techie together");
    }

    @Test
    public void testEventCanBeDeletedByName(){
        RemoveEventRequest request1 = new RemoveEventRequest();
        request1.setEventName("3");
        RemoveEventResponse response1 = adminServices.removeEventByName(request1, "3");
        assertThat(response1.getMessage().contains("successfully removed"));

    }

    @Test
    public void testThatAllEventsCanBeRetrieved(){
        List<Event> response = adminServices.viewEvent();
        assertThat(response).isNotNull();
    }

    @Test
    public void testFindEventByLocation(){
        FindEventByLocationRequest request = new FindEventByLocationRequest();
        request.setEventName("tech");
        request.setLocation("1");
        List<Event> response = adminServices.findEventByEventLocation(request);
        assertThat(response).isNotNull();

    }

}
