package org.eventticket.eventticketapp.services;

import lombok.RequiredArgsConstructor;
import org.eventticket.eventticketapp.data.model.Event;
import org.eventticket.eventticketapp.data.repository.EventRepository;
import org.eventticket.eventticketapp.dto.request.*;
import org.eventticket.eventticketapp.dto.response.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminServices {

    private final EventRepository eventRepository;


    @Override
    public BookEventResponse createEvent(BookEventRequest request) {
        validateEventName(request.getEventName());
        Event event = new Event();
        event.setEventName(request.getEventName());
        event.setEventLocation(request.getEventLocation());
        event.setEventDescription(request.getEventDescription());
        event.setEventDateAndTime((request.getEventDateAndTime()));
        event.setPrice(request.getPrice());
        eventRepository.save(event);
        BookEventResponse response = new BookEventResponse();
        response.setEventName(event.getEventName());
        response.setEventLocation(event.getEventLocation());
        response.setEventDescription(event.getEventDescription());
        response.setEventDateAndTime(event.getEventDateAndTime());
        response.setPrice(event.getPrice());
        return response;
    }

    private void validateEventName(String eventName) {
        Optional <Event> validatingEmail = eventRepository.findEventByEventName(eventName);
        if(validatingEmail.isPresent()) {
            throw new IllegalArgumentException("Event name is already used");
        }

        if (eventName.length() > 50) {
            throw new IllegalArgumentException("Event name is too long");
        }
    }

    @Override
    public EventUpdateResponse updateEvent(EventUpdateRequest request, String name) {
        Optional<Event> event = eventRepository.findEventByEventName(name);
        if (event.isPresent()) {
            Event updatedEvent = event.get();
            updatedEvent.setEventName(request.getEventName());
            updatedEvent.setEventLocation(request.getEventLocation());
            updatedEvent.setEventDescription(request.getEventDescription());
            updatedEvent.setEventDateAndTime(request.getEventDateAndTime());
            eventRepository.save(updatedEvent);
            EventUpdateResponse response = new EventUpdateResponse();
            response.setEventName(updatedEvent.getEventName());
            response.setEventLocation(updatedEvent.getEventLocation());
            response.setEventDescription(updatedEvent.getEventDescription());
            response.setEventDateAndTime(updatedEvent.getEventDateAndTime());
            return response;
        }
        throw new IllegalArgumentException("Event not found");
    }


    @Override
    public RemoveEventResponse removeEventByName(RemoveEventRequest request, String eventName) {
        Optional <Event> eventToRemove = eventRepository.findEventByEventName(eventName);
       if (eventToRemove.isPresent()) {
           Event event = eventToRemove.get();
           eventRepository.delete(event);
       }
       RemoveEventResponse response = new RemoveEventResponse();
       response.setMessage("Event removed successfully");
       return response;
    }

    @Override
    public List<Event> viewEvent() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findEventByEventLocation(String eventLocation) {

        Event findEventLocation = eventRepository.findEventByEventLocation(eventLocation);
        List<Event> events = new ArrayList<>();

        for(Event event: eventRepository.findAll()) {
            if(event.getEventLocation().equals(findEventLocation.getEventLocation())) {
                events.add(event);
                return events;
            }
        }
        return events;
    }

    @Override
    public Event findEventByName(String eventName) {
        Optional<Event> findEvents = eventRepository.findEventByEventName(eventName);
        if (findEvents.isPresent()) {
            return findEvents.get();
        }
        throw new IllegalArgumentException("Event not found");
    }

    @Override
    public List<String> findEventByPrice(String price) {

        return eventRepository.findAll().stream().map(Event::getEventLocation).collect(Collectors.toList());

    }
}
